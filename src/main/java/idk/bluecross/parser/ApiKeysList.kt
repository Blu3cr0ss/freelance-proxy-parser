package idk.bluecross.parser

import com.fasterxml.jackson.annotation.JsonValue
import idk.bluecross.connection.service.ApiKeysListService
import idk.bluecross.util.DIUtil

class ApiKeysList(@JsonValue val keyString: String) : ArrayList<String>() {

    constructor() : this("")        // <--- нужно для десериалайзера
    private var ApiKeysListService = DIUtil.getBean(ApiKeysListService::class.java)

    init {
        this.addAll(keyString.split("\n"))
    }

    fun getWorkingApiKey(): String {
        return this.first { ApiKeysListService.isWorking(it) }
    }

    fun setNotWorking(key: String) {
        ApiKeysListService.setNotWorking(key)
    }
}