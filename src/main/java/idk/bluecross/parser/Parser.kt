package idk.bluecross.parser

import idk.bluecross.GlobalSettings
import idk.bluecross.connection.service.BlackListService
import idk.bluecross.connection.service.BusyProxyService
import idk.bluecross.parser.proxy.AbstractProxy
import idk.bluecross.util.DIUtil
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Parser(
    val proxiesString: String,
    val apiKeysList: ApiKeysList,
    val blackList: List<String>,
    val maxFraudScore: Int = 0
) {

    var BlackListService = DIUtil.getBean(BlackListService::class.java)

    var BusyProxyService = DIUtil.getBean(BusyProxyService::class.java)

    var proxyFlux by ProxyFlux(proxiesString)

    private fun <E> String.indexesOf(e: E) = mapIndexedNotNull { index, elem -> index.takeIf { elem == e } }

    fun filterBlacklist() = proxyFlux
        .publishOn(Schedulers.boundedElastic())
        .subscribeOn(Schedulers.boundedElastic())
        .filter {
            val _blackList = blackList.map { BlackListService.get(it) }.filterNotNull()
            for (bl in _blackList) {
                when (bl.matchType) {
                    BlackList.MatchType.OCTET2 -> {
                        val octet2index = it.ip.indexesOf(".")[1]
                        val octet2 = it.ip.substring(0, octet2index)
                        return@filter bl.filter { it.startsWith(octet2) }.isEmpty()
                    }

                    BlackList.MatchType.OCTET3 -> {
                        val octet3index = it.ip.indexesOf(".")[2]
                        val octet3 = it.ip.substring(0, octet3index)
                        return@filter bl.filter { it.startsWith(octet3) }.isEmpty()
                    }

                    BlackList.MatchType.FULL -> {
                        return@filter bl.filter { ip -> ip == it.ip }.isEmpty()
                    }
                }
            }
            true
        }

    fun filterFraudScore(maxScore: Int = 0) = proxyFlux
        .publishOn(Schedulers.boundedElastic())
        .subscribeOn(Schedulers.boundedElastic())
        .filter {
            val apiKey = apiKeysList.getWorkingApiKey()

            fun filterFunc(): Boolean {
                val url = URL("http://ipqualityscore.com/api/json/ip/$apiKey/${it.ip}")

                val content: String
                with(url.openConnection() as HttpURLConnection) {
                    connectTimeout = GlobalSettings.PARSER.fraudScoreConnectTimeout
                    readTimeout = GlobalSettings.PARSER.fraudScoreReadTimeout
                    content = InputStreamReader((this.content as InputStream)).readText()
                }
                val score =
                    Regex("\\\"fraud_score\\\":\\d+").find(content)?.value?.filter { it.code in 48..57 } //0 to 9 chars
                        ?.toList()?.joinToString("")?.toInt() ?: return false
                it.fraudScore = score
                if (score <= maxScore) {
                    return true
                } else
                    return false
            }

            try {
                return@filter filterFunc()
            } catch (e: Exception) {
                e.printStackTrace()
                apiKeysList.setNotWorking(apiKey)
                return@filter runCatching { filterFunc() }.getOrElse { false }
            }
        }

    fun filterBusy() = proxyFlux
        .publishOn(Schedulers.boundedElastic())
        .subscribeOn(Schedulers.boundedElastic())
        .filter {
            !BusyProxyService.isBusy(it.ip)
        }

    fun detectIpV6() {

    }

    fun parse(): Flux<AbstractProxy.RealProxy> {
        proxyFlux = filterBlacklist()
        proxyFlux = filterFraudScore(maxFraudScore)
        proxyFlux = filterBusy()
        proxyFlux = proxyFlux.distinct(AbstractProxy.RealProxy::ip)

        return proxyFlux
    }
}