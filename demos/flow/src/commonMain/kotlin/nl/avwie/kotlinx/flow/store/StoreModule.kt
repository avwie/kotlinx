package nl.avwie.kotlinx.flow.store

import org.kodein.di.DI
import org.kodein.di.bindSingleton

object StoreModule {
    operator fun invoke() = DI.Module(name = "Store") {
        bindSingleton {
            ElementsStore()
        }
    }
}