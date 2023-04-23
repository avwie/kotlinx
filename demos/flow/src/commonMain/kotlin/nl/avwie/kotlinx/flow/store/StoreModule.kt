package nl.avwie.kotlinx.flow.store

import nl.avwie.kotlinx.flow.state.Icon
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object StoreModule {
    operator fun invoke(
        initialIcons: List<Icon> = listOf()
    ) = DI.Module(name = "Store") {
        bindSingleton {
            IconsStore(
                initialIcons = initialIcons
            )
        }
    }
}