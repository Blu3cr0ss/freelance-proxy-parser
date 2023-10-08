package idk.bluecross.connection.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import idk.bluecross.connection.util.BlackListDeserializer
import idk.bluecross.connection.util.BlackListSerializer
import idk.bluecross.parser.BlackList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
open class jackson2ObjectMapperBuilderConfig : Jackson2ObjectMapperBuilder() {

    fun getBlackListSerializationModule() = SimpleModule("BlackList_S-DS").apply {
        addSerializer(BlackListSerializer())
        addDeserializer(BlackList::class.java, BlackListDeserializer())
    }


    @Bean
    @Primary
    open fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder()
            .modules(getBlackListSerializationModule(), *(ObjectMapper.findModules().toTypedArray()))
            .serializationInclusion(JsonInclude.Include.NON_NULL)
    }
}