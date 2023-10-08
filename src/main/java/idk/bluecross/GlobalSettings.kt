package idk.bluecross

object GlobalSettings {
    enum class Scheduling {
        DEFAULT
    }

    var SCHEDULING = Scheduling.DEFAULT

    object PROXY {
        var connectTimeout = 30000
        var readTimeout = 30000
    }

    object PARSER {
        val fraudScoreConnectTimeout = 10000
        val fraudScoreReadTimeout = 10000
    }
}