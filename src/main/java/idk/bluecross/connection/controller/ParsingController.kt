package idk.bluecross.connection.controller

import idk.bluecross.GlobalSettings
import idk.bluecross.connection.dto.ParseDTO
import idk.bluecross.parser.Parser
import idk.bluecross.parser.proxy.AbstractProxy
import idk.bluecross.parser.proxy.RealProxy
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.Duration
import org.springframework.web.bind.annotation.CrossOrigin
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/parser")
@CrossOrigin
class ParsingController {
    @PostMapping(
        "/parse",
//        produces = ["application/stream+json"]
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )

    fun startParsing(
        @RequestBody data: ParseDTO
    ): Flux<RealProxy> {
        when (GlobalSettings.SCHEDULING) {
            GlobalSettings.Scheduling.DEFAULT -> {
//                return Parser(data.proxy, data.apiKeys, data.blackList, data.maxFraudScore).parse()
                return Flux.just(       // Заглушка - Ссылки устарели (ParseDTO.proxy)
                    RealProxy("123.456.789.123").apply {
                        fraudScore = 0
                        fakeIp = "fakeIp.com:555/api/changeIp?token=123456"
                    },
                    RealProxy("123.456.789.124").apply {
                        fraudScore = 1
                        fakeIp = "fakeIp.com:555/api/changeIp?token=123457"
                    }, RealProxy("123.456.789.125").apply {
                        fraudScore = 2
                        fakeIp = "fakeIp.com:555/api/changeIp?token=123458"
                    }, RealProxy("123.456.789.126").apply {
                        fraudScore = 3
                        fakeIp = "fakeIp.com:555/api/changeIp?token=123459"
                    }, RealProxy("123.456.789.127").apply {
                        fraudScore = 4
                        fakeIp = "fakeIp.com:555/api/changeIp?token=123450"
                    }
                ).delayElements(Duration.ofSeconds(2))
            }
        }
    }
}
