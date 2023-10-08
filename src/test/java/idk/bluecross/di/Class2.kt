package idk.bluecross.di

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Configurable
class Class2(val name:String) {

//    var dependency: Dependency = Q.appContext.getBean(Dependency::class.java)

//    @Autowired lateinit var dependency: Dependency

  var dependency: Dependency = BeanUtils.instantiateClass(Dependency::class.java)



    fun greeting() {
        dependency.sayHi()
        println("My name is $name")
    }
}