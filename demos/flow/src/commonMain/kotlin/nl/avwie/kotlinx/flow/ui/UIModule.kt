package nl.avwie.kotlinx.flow.ui

import nl.avwie.kotlinx.flow.ui.flow.FlowViewModel
import nl.avwie.kotlinx.flow.ui.icons.IconsViewModel
import nl.avwie.kotlinx.flow.ui.selector.SelectorViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object UIModule {
    operator fun invoke() = DI.Module(name = "UI") {

        bindProvider {
            FlowViewModel(
                icons = instance(),
                selector = instance(),
            )
        }

        bindProvider {
            IconsViewModel(
                observeIcons = instance(),
                observeSelectionBox = instance(),
                selectIcon = instance(),
                moveIcon = instance(),
            )
        }

        bindProvider {
            SelectorViewModel(
                selectionBox = instance(),
                observeSelectionBox = instance(),
            )
        }
    }
}