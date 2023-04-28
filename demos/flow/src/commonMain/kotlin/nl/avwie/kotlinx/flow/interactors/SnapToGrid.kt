package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface SnapToGrid {

    sealed interface Mode {
        object Default : Mode

        data class SnapBack(val originalPosition: DpOffset) : Mode
    }
    fun snapToGrid(iconState: IconState, mode: Mode = Mode.Default)
}

class SnapToGridImpl(
    private val iconsStore: IconsStore,
    private val gridPosition: GridPosition,
    private val moveIcon: MoveIcon
) : SnapToGrid {
    override fun snapToGrid(iconState: IconState, mode: SnapToGrid.Mode) {
        val (row, col) = gridPosition.getRowAndColumn(iconState.centroid)
        val newPosition = gridPosition.getPosition(row, col, mode = GridPosition.Mode.Centroid)

        if (mode is SnapToGrid.Mode.SnapBack) {
            val otherIcons = iconsStore.icons.value.filter { it.id != iconState.id }
            if (otherIcons.any { gridPosition.getRowAndColumn(it.position) == row to col }) {
                moveIcon.absolute(iconState, mode.originalPosition, mode = MoveIcon.Mode.Centroid)
                return
            }
        }

        moveIcon.absolute(iconState, newPosition, mode = MoveIcon.Mode.Centroid)
    }
}