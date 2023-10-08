package idk.bluecross.connection.service

import org.springframework.stereotype.Component
import java.io.File

@Component
class BusyProxyService {
    val jarDir = File(this::class.java.protectionDomain.codeSource.location.toURI()).parentFile
    val busyProxiesDir = File(jarDir, "busyProxies").also {
        if (!it.exists()) it.mkdirs()
    }
    val busyProxiesFile = File(busyProxiesDir, "busyProxies").also {
        if (!it.exists()) it.createNewFile()
    }

    var listOfProxies: ArrayList<String> = ArrayList(busyProxiesFile.readText().split("\n"))

    fun take(proxy: String) {
        if (!listOfProxies.contains(proxy)) {
            listOfProxies.add(proxy)
            busyProxiesFile.writeText((busyProxiesFile.readText()+ proxy+"\n"))
            listOfProxies = ArrayList(busyProxiesFile.readText().split("\n"))
        }
    }

    fun giveBack(proxy: String) {
        if (listOfProxies.contains(proxy)) {
            listOfProxies.remove(proxy)
            busyProxiesFile.writeText(busyProxiesFile.readText().replaceFirst(proxy+"\n", ""))
            listOfProxies = ArrayList(busyProxiesFile.readText().split("\n"))
        }
    }

    fun getBusy() = listOfProxies
    fun isBusy(proxy: String) = listOfProxies.contains(proxy)
}