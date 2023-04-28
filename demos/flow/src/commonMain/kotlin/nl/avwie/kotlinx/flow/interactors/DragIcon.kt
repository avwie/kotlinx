package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import com.benasher44.uuid.Uuid
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface DragIcon {

    sealed interface Mode {
        object Default : Mode

        data class SnapToGrid(val snapBack: Boolean): Mode
    }

    fun start(iconState: IconState)

    fun drag(iconState: IconState, offset: DpOffset)

    fun end(iconState: IconState, mode: Mode = Mode.Default)
}

class DragIconImpl(
    private val iconsStore: IconsStore,
    private val gridPosition: GridPosition,
    private val moveIcon: MoveIcon
) : DragIcon {

    private val startingPositions = mutableMapOf<Uuid, DpOffset>()
    override fun start(iconState: IconState) {
        startingPositions[iconState.id] = iconState.position
    }

    override fun drag(iconState: IconState, offset: DpOffset) {
        iconsStore.updateIcon(iconState) {
            it.copy(position = it.position + offset)
        }
    }

    override fun end(iconState: IconState, mode: DragIcon.Mode) {

        if (mode is DragIcon.Mode.SnapToGrid) {
            val (row, col) = gridPosition.getRowAndColumn(iconState.centroid)
            val newPosition = gridPosition.getPosition(row, col, mode = GridPosition.Mode.Centroid)

            if (mode.snapBack) {
                val otherIcons = iconsStore.icons.value.filter { it.id != iconState.id }
                if (otherIcons.any { gridPosition.getRowAndColumn(it.position) == row to col }) {
                    iconsStore.updateIcon(iconState) {
                        it.copy(position = startingPositions[iconState.id]!!)
                    }
                } else {
                    moveIcon(iconState, newPosition, mode = MoveIcon.Mode.Centroid)
                }
            } else {
                moveIcon(iconState, newPosition, mode = MoveIcon.Mode.Centroid)
            }
        }

        startingPositions.remove(iconState.id)
    }
}