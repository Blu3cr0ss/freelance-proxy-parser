package idk.bluecross.proxy

import idk.bluecross.parser.proxy.ProxyV2
import org.junit.jupiter.api.Test

class ProxyV2Test {
    @Test
    suspend fun test() {
        val p = ProxyV2("node-de-96.astroproxy.com:10653/api/changeIP?apiToken=49fbea178cdf4dc0")
        p.getRealProxy()
        println(p.realProxy?.ip)

    }
}