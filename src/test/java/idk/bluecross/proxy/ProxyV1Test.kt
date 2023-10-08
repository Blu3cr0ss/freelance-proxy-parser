package idk.bluecross.proxy

import idk.bluecross.parser.proxy.ProxyV1
import org.junit.jupiter.api.Test

class ProxyV1Test {
    @Test
    suspend fun test() {
        val p = ProxyV1("mydearfriend3357:2431cf@162.19.196.77:10247")
        p.getRealProxy()
        println(p.realProxy?.ip)
    }
}