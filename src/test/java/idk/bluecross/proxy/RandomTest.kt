package idk.bluecross.proxy

import org.junit.jupiter.api.Test
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RandomTest {
    @Test
    fun intCharCode() {
        println('1'.code)
        println('9'.code)
    }

    @Test
    fun fraudScore() {
        val url = URL("http://ipqualityscore.com/api/json/ip/d57AHTTy8U7kfyx0te0dZHYlCIeP4apD/94.16.128.38")

        val content: String
        with(url.openConnection() as HttpURLConnection) {
            connectTimeout = 10000
            readTimeout = 10000
            content = InputStreamReader((this.content as InputStream)).readText()
        }

        val score =
            Regex("\\\"fraud_score\\\":\\d++").find(content, 35)?.value?.filter { it.code in 48..57 } //0 to 9 chars
                ?.toList()?.joinToString("")?.toInt()
        println(score)
    }


}