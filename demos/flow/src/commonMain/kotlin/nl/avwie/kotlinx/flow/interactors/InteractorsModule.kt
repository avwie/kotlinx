package nl.avwie.kotlinx.flow.interactors

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object InteractorsModule {
    operator fun invoke() = DI.Module(name = "Interactors") {
        bindProvider<MoveIcon> {
            MoveIconImpl(
                iconsStore = instance()
            )
        }

        bindProvider<SelectIcon> {
            SelectIconImpl(
                iconsStore = instance()
            )
        }

        bindProvider<Selector> {
            SelectorImpl(
                selectorStore = instance()
            )
        }
    }
}