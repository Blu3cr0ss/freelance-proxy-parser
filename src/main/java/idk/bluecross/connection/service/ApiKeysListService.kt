package idk.bluecross.connection.service

import org.springframework.stereotype.Component
import java.io.File

@Component
class ApiKeysListService {
    val jarDir = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile
    val apiKeysDir = File(jarDir, "expiredApiKeys").also {
        if (!it.exists()) it.mkdirs()
    }
    val expiredKeysFile = File(apiKeysDir, "expiredApiKeys").also {
        if (!it.exists()) it.createNewFile()
    }

    var listOfKeys: ArrayList<String> = ArrayList(expiredKeysFile.readText().split("\n"))

    fun isWorking(key: String) = !listOfKeys.contains(key)

    fun setNotWorking(key: String) {
        if (!listOfKeys.contains(key)) {
            listOfKeys.add(key)
            expiredKeysFile.writeText((expiredKeysFile.readText() + key + "\n"))
            listOfKeys = ArrayList(expiredKeysFile.readText().split("\n"))
        }
    }

    fun deleteNotWorking(key: String) {
        if (listOfKeys.contains(key)) {
            listOfKeys.remove(key)
            expiredKeysFile.writeText(expiredKeysFile.readText().replaceFirst(key + "\n", ""))
            listOfKeys = ArrayList(expiredKeysFile.readText().split("\n"))
        }
    }

}