package idk.bluecross.connection.dto

import idk.bluecross.parser.ApiKeysList


data class ParseDTO(
    val proxy: String,
    val apiKeys: ApiKeysList,
    val blackList: List<String>,
    val maxFraudScore: Int = 0
)
