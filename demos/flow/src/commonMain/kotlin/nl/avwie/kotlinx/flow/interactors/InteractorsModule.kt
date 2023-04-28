package nl.avwie.kotlinx.flow.interactors

import nl.avwie.kotlinx.flow.interactors.grid.*
import nl.avwie.kotlinx.flow.interactors.icons.*
import nl.avwie.kotlinx.flow.interactors.selector.Selector
import nl.avwie.kotlinx.flow.interactors.selector.SelectorImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object InteractorsModule {
    operator fun invoke() = DI.Module(name = "Interactors") {
        bindProvider<MoveIcon> {
            MoveIconImpl(
                iconsStore = instance(),
                gridPosition = instance()
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

        bindProvider<UpdateGrid> {
            UpdateGridImpl(
                gridStore = instance()
            )
        }

        bindProvider<GridPosition> {
            GridPositionImpl(
                gridStore = instance()
            )
        }

        bindProvider<SnapToGrid> {
            SnapToGridImpl(
                iconsStore = instance(),
                gridPosition = instance(),
                moveIcon = instance()
            )
        }
    }
}