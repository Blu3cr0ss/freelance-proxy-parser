package idk.bluecross

import idk.bluecross.util.DIUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
open class ParserToolApplication {
    @Autowired
    fun setAppCtx(appCtx: ApplicationContext) {
        DIUtil.setAppCtx(appCtx)
    }
}

fun main() {
    SpringApplication.run(ParserToolApplication::class.java)
}