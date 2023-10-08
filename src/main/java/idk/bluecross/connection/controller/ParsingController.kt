package idk.bluecross.connection.controller

import idk.bluecross.GlobalSettings
import idk.bluecross.connection.dto.ParseDTO
import idk.bluecross.parser.Parser
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/parser")
class ParsingController {
    @PostMapping("/parse")
    fun startParsing(
        @RequestBody data: ParseDTO
    ): Any {
        when (GlobalSettings.SCHEDULING) {
            GlobalSettings.Scheduling.DEFAULT -> {
                return Parser(data.proxy, data.apiKeys, data.blackList, data.maxFraudScore).parse()
            }
        }
    }
}