package idk.bluecross.connection.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import idk.bluecross.parser.BlackList
import org.springframework.core.serializer.Serializer
import java.io.OutputStream

class BlackListSerializer : StdSerializer<BlackList>(BlackList::class.java) {

    override fun serialize(value: BlackList?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeStartObject();
        gen?.writeStringField("name", value?.name);
        gen?.writeStringField("ipString", value?.ipString);
        gen?.writeStringField("matchType", value?.matchType.toString());
        gen?.writeEndObject();
    }

}