package idk.bluecross.util

import org.springframework.context.ApplicationContext

object DIUtil {
    private lateinit var appCtx: ApplicationContext
    fun setAppCtx(appCtx: ApplicationContext) {
        if (!::appCtx.isInitialized)
            this.appCtx = appCtx
    }

    fun <T>getBean(clazz: Class<T>) = appCtx.getBean(clazz)
}