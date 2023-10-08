package idk.bluecross.di

import org.springframework.stereotype.Component

@Component
class Dependency {
    fun sayHi() = println("Hi!")
}