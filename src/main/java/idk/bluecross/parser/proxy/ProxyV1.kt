package idk.bluecross.parser.proxy

import idk.bluecross.GlobalSettings
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

// mydearfriend3357:2431cf@5.9.244.199:10653

class ProxyV1(override val stringRepresentation: String) : AbstractProxy(stringRepresentation) {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun getRealProxy(): RealProxy? {
        if (realProxy!= null) return realProxy
        try {
            val unamePassword_ipPort = stringRepresentation.split("@")
            val username = unamePassword_ipPort[0].split(":")[0]
            val password = unamePassword_ipPort[0].split(":")[1]
            val ip = unamePassword_ipPort[1].split(":")[0]
            val port = unamePassword_ipPort[1].split(":")[1].toInt()

            val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(ip, port))
            val authHeader = Base64.encode(("$username:$password").toByteArray())
            val realIp: String

            with(URL("http://checkip.amazonaws.com/").openConnection(proxy) as HttpURLConnection) {
                setRequestProperty("Proxy-Authorization", "Basic $authHeader");
                connectTimeout = GlobalSettings.PROXY.connectTimeout
                readTimeout = GlobalSettings.PROXY.readTimeout
                connect()
                if (responseCode in 200..299) {
                    realIp = InputStreamReader((this.content as InputStream)).readText().replace("\n", "")
                    if (realIp.contains("Exception")) throw RuntimeException("Connection error")
                    realProxy = RealProxy(realIp)
                } else {
                    println("Прокси $stringRepresentation вернул код $responseCode")
                }
            }
        } catch (e: Exception) {
            println("$stringRepresentation: ошибка соединения")
//            e.printStackTrace()
        }
        return realProxy
    }
}