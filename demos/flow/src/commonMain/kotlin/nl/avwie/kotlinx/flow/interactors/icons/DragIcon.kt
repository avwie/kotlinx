package nl.avwie.kotlinx.flow.interactors.icons

import androidx.compose.ui.unit.DpOffset
import com.benasher44.uuid.Uuid
import nl.avwie.kotlinx.flow.interactors.grid.SnapToGrid
import nl.avwie.kotlinx.flow.state.IconState

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
    private val moveIcon: MoveIcon,
    private val snapToGrid: SnapToGrid
) : DragIcon {

    private val startingPositions = mutableMapOf<Uuid, DpOffset>()
    override fun start(iconState: IconState) {
        startingPositions[iconState.id] = iconState.position
    }

    override fun drag(iconState: IconState, offset: DpOffset) {
        moveIcon.relative(iconState, offset)
    }

    override fun end(iconState: IconState, mode: DragIcon.Mode) {

        if (mode is DragIcon.Mode.SnapToGrid) {
            snapToGrid.snapToGrid(iconState, mode = SnapToGrid.Mode.SnapBack(startingPositions[iconState.id]!!))
        }

        startingPositions.remove(iconState.id)
    }
}