package idk.bluecross.parser

import idk.bluecross.parser.proxy.AbstractProxy
import idk.bluecross.parser.proxy.RealProxy
import idk.bluecross.util.ProxyUtil
import kotlinx.coroutines.reactor.mono
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import kotlin.reflect.KProperty

class ProxyFlux(val proxiesString: String) {

    var flux: Flux<RealProxy> = proxiesStringToRealProxyList()

    private fun proxiesStringToRealProxyList() = Flux.fromIterable(proxiesString.split("\n", "|"))
        .publishOn(Schedulers.boundedElastic())
        .subscribeOn(Schedulers.boundedElastic())
        .distinct()
        .flatMap { str ->
            mono {
                ProxyUtil.determineProxyType(str)?.constructors?.first()?.call(str)?.run{getRealProxy()
                    ?.also { rp -> rp.fakeIp = stringRepresentation }}
            }
        }
//        .doOnNext { println(it.ip) }
//        .also { flux = it }


    operator fun getValue(thisRef: Any?, property: KProperty<*>): Flux<RealProxy> {
        return flux
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Flux<RealProxy>) {
        flux = value
    }

}