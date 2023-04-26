package nl.avwie.kotlinx.flow.observers

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

        bindProvider<ObserveSelectionBox> {
            ObserveSelectionBoxImpl(
                store = instance()
            )
        }
    }
}