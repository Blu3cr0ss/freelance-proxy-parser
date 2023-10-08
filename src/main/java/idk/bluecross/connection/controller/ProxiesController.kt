package idk.bluecross.connection.controller

import idk.bluecross.connection.service.BusyProxyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/proxy")
class ProxiesController {

    @Autowired
    lateinit var BusyProxyService: BusyProxyService


    @PostMapping("/take")
    fun take(@RequestBody proxy: String) {
        BusyProxyService.take(proxy)
    }

    @PostMapping("giveBack")
    fun giveBack(@RequestBody proxy: String) {
        BusyProxyService.giveBack(proxy)
    }

    @GetMapping("/getBusy")
    fun getBusy(): ArrayList<String> {
        return BusyProxyService.getBusy()
    }

    @GetMapping("/isBusy")
    fun isBusy(@RequestBody proxy: String): Boolean {
        return BusyProxyService.isBusy(proxy)
    }

}