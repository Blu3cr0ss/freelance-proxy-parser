package idk.bluecross.parser.proxy

class RealProxy(val ip: String) {
    var fraudScore = -1
    lateinit var fakeIp: String
}