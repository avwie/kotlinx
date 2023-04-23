package nl.avwie.kotlinx.flow.observers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object ObserversModule {
    operator fun invoke() = DI.Module(name = "Observers") {

        bindProvider<ObserveIcons> {
            ObserveIconsImpl(
                iconsStore = instance()
            )
        }
    }
}