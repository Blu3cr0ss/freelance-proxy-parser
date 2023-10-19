package idk.bluecross.connection.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/api/test")
@CrossOrigin
class TestController {
    @PostMapping("webFlux",
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun webFlux(): Flux<JsonElement> {
        return Flux.just(JsonElement("test1"), JsonElement("test2"), JsonElement("test3")).delayElements(Duration.ofSeconds(3))
    }

    data class JsonElement(val text:String)
}