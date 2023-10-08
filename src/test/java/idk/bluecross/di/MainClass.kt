package idk.bluecross.di

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class MainClass {
    @Autowired
    lateinit var appContext: ApplicationContext

    @Test
    fun test() {
//        Q.appContext = appContext
        Class2("Andrew").greeting()
        Class2("Dasha").greeting()

    }
}