package idk.bluecross.parser.proxy

import com.fasterxml.jackson.databind.ObjectMapper
import idk.bluecross.GlobalSettings
import idk.bluecross.util.DIUtil
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// node-de-96.astroproxy.com:10653/api/changeIP?apiToken=49fbea178cdf4dc0


class ProxyV2(override val stringRepresentation: String) : AbstractProxy(stringRepresentation) {

    var mapper = DIUtil.getBean(ObjectMapper::class.java)

    override suspend fun getRealProxy(): RealProxy? {
        if (realProxy != null) return realProxy
        try {
            val url = URL("http://" + stringRepresentation)
            val content: InputStream
            with(url.openConnection() as HttpURLConnection) {
                connectTimeout = GlobalSettings.PROXY.connectTimeout
                readTimeout = GlobalSettings.PROXY.readTimeout
                content = this.content as InputStream
            }
            realProxy = RealProxy(mapper.readTree(content).get("IP").textValue())
        } catch (e: IOException) {
            if (e.message?.contains("Server returned HTTP response code: 429") == true)
                println("Прокси ${stringRepresentation} вернул ошибку 429 - слишком много запросов")
        } catch (e: Exception) {
            println("$stringRepresentation: ошибка соединения")
//            e.printStackTrace()
        }
        return realProxy
    }
}