package idk.bluecross.util

import idk.bluecross.parser.proxy.AbstractProxy
import idk.bluecross.parser.proxy.ProxyV1
import idk.bluecross.parser.proxy.ProxyV2
import kotlin.reflect.KClass

object ProxyUtil {

    val proxyRegexMap = hashMapOf(
        "^(.+:{1}.+)@{1}([\\d\\.]+:{1}\\d+)$" to ProxyV1::class,
        "^.+(/api/changeIP\\?apiToken=).+\$" to ProxyV2::class
    )

    fun determineProxyType(proxyString: String): KClass<out AbstractProxy>? =
        proxyRegexMap.entries.firstOrNull {
            proxyString.matches(Regex(it.key))
        }?.value


}