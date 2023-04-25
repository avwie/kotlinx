package nl.avwie.kotlinx.flow.ui

import nl.avwie.kotlinx.flow.ui.flow.FlowViewModel
import nl.avwie.kotlinx.flow.ui.icons.IconsViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object UIModule {
    operator fun invoke() = DI.Module(name = "UI") {

        bindProvider {
            FlowViewModel(
                icons = instance()
            )
        }

        bindProvider {
            IconsViewModel(
                observeIcons = instance(),
                selectIcon = instance(),
                moveIcon = instance(),
            )
        }
    }
}