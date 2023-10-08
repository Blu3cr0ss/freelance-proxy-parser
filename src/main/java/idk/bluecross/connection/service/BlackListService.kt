package idk.bluecross.connection.service

import com.fasterxml.jackson.databind.ObjectMapper
import idk.bluecross.parser.BlackList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File


@Component
class BlackListService {
    private val ILLEGAL_CHARACTERS =
        charArrayOf('/', '\n', '\r', '\t', '\u0000', '\u000c', '`', '?', '*', '\\', '<', '>', '|', '\"', ':')

    fun validateName(name: String) = name.map { if (ILLEGAL_CHARACTERS.contains(it)) '-' else it }.joinToString("")

    @Autowired
    private lateinit var mapper: ObjectMapper

    val jarDir = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile
    val blackListsDir = File(jarDir, "blackLists").also {
        if (!it.exists()) it.mkdirs()
    }
    val blacklistsArr
        get() = blackListsDir.listFiles().toList().filter { it.name.startsWith("blacklist") && it.extension == "json" }

    fun get(name: String) =
        with(blacklistsArr.firstOrNull {
            it.name == "blacklist_" + validateName(name) + ".json"
        }) {
            if (this == null) return@with null
            mapper.readValue(this.readText(), BlackList::class.java)
        }

    fun save(blacklist: BlackList) {
        blacklist.name = validateName(blacklist.name)
        mapper.writeValue(
            File(
                blackListsDir,
                "blacklist_" + validateName(blacklist.name) + ".json"
            ).also {
                if (!it.exists()) it.createNewFile()
            }, blacklist
        )
    }

    fun delete(name: String) {
        blacklistsArr.firstOrNull { it.name == "blacklist_" + name + ".json" }?.delete()
    }

    fun getAll() = blacklistsArr.map { mapper.readValue(it, BlackList::class.java) }

}