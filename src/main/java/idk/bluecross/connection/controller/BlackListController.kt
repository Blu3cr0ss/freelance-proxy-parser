package idk.bluecross.connection.controller

import idk.bluecross.connection.service.BlackListService
import idk.bluecross.parser.BlackList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/blacklist")
class BlackListController {

    @Autowired
    lateinit var BlackListService: BlackListService

    @PostMapping("/save")
    fun save(
        @RequestBody
        blackList: BlackList
    ) {
        BlackListService.save(blackList)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestBody name: String) {
        BlackListService.delete(name)
    }

    @GetMapping("/get")
    fun get(@RequestBody name: String) = BlackListService.get(name)

    @GetMapping("/getAll")
    fun getAll() = BlackListService.getAll()
}