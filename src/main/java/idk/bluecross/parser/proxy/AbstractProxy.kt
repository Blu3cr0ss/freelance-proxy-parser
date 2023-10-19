package idk.bluecross.parser.proxy

abstract class AbstractProxy(open val stringRepresentation: String) {

    abstract suspend fun getRealProxy(): RealProxy?

    var realProxy: RealProxy? = null
}