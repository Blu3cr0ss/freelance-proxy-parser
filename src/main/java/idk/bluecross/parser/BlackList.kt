package idk.bluecross.parser

class BlackList(var name: String, var ipString: String, var matchType: MatchType) : ArrayList<String>() {
    init {
        this.addAll(ipString.split("\n"))
    }

    constructor() : this("", "", MatchType.FULL)

    enum class MatchType {
        OCTET2,
        OCTET3,
        FULL
    }
}