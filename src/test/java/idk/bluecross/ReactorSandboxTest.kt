package idk.bluecross

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class ReactorSandboxTest {
    fun nonBlockingMethod1sec(data: String) = Mono.just(data).delayElement(Duration.ofMillis(1000))

    @Test
    fun testLevel2() {
        val result = nonBlockingMethod1sec("Hello world")
            .flatMap { nonBlockingMethod1sec(it) }
            .block()

        assertEquals("Hello world", result)
    }

    @Test
    fun fluxes() {
        val flux = Flux.just(1, 2, 3, 4, 5)
        flux.filter { it < 3 }

        println(flux.collectList().block())

    }
}