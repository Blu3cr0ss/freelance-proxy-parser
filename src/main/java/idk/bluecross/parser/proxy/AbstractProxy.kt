package idk.bluecross.parser.proxy

abstract class AbstractProxy(open val stringRepresentation: String) {

    abstract suspend fun getRealProxy(): RealProxy?

    var realProxy: RealProxy? = null

    inner class RealProxy(val ip: String) {
        var fraudScore = -1
        lateinit var fakeIp: String
    }
}