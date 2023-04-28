package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface MoveIcon {

    sealed interface Mode {
        object TopLeft : Mode
        object Centroid : Mode
    }

    fun absolute(iconState: IconState, position: DpOffset, mode: Mode = Mode.TopLeft)
    fun relative(iconState: IconState, offset: DpOffset, mode: Mode = Mode.TopLeft) {
        absolute(iconState, iconState.position + offset, mode)
    }
}

class MoveIconImpl(
    private val iconsStore: IconsStore,
    private val gridPosition: GridPosition
) : MoveIcon {

    override fun absolute(iconState: IconState, position: DpOffset, mode: MoveIcon.Mode) {
        val newPosition = when (mode) {
            MoveIcon.Mode.TopLeft -> position
            MoveIcon.Mode.Centroid -> position - DpOffset(iconState.type.size.width / 2, iconState.type.size.height / 2)
        }
        iconsStore.updateIcon(iconState) {
            it.copy(position = newPosition)
        }
    }
}