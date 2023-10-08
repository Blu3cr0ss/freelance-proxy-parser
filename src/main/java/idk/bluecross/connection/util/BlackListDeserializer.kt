package idk.bluecross.connection.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import idk.bluecross.parser.BlackList
import idk.bluecross.parser.BlackList.MatchType
import java.lang.RuntimeException


class BlackListDeserializer : JsonDeserializer<BlackList>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): BlackList {
        val node: JsonNode = p!!.codec.readTree(p)
        val input = node.get("ipString").asText()
        val matchType = MatchType.valueOf(node.get("matchType").asText())
        val name = node.get("name").asText()
        return BlackList(name, input, matchType)
    }
}