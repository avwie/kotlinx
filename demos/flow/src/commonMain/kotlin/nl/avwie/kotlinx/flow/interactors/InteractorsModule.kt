package nl.avwie.kotlinx.flow.interactors

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

        bindProvider<DragIcon> {
            DragIconImpl(
                moveIcon = instance(),
                snapToGrid = instance()
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