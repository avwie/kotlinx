package nl.avwie.kotlinx.flow.interactors.grid

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.store.GridStore

interface GridPosition {

    sealed interface Mode {
        object TopLeft : Mode
        object Centroid : Mode
    }
    fun getRowAndColumn(position: DpOffset): Pair<Int, Int>
    fun getPosition(row: Int, col: Int, mode: Mode = Mode.TopLeft): DpOffset
}

class GridPositionImpl(
    private val gridStore: GridStore
) : GridPosition {
    override fun getRowAndColumn(position: DpOffset): Pair<Int, Int> {
        val grid = gridStore.gridDefinition.value
        val x = (position.x / grid.columnWidth).toInt()
        val y = (position.y / grid.rowHeight).toInt()
        return y to x
    }

    override fun getPosition(row: Int, col: Int, mode: GridPosition.Mode): DpOffset {
        val grid = gridStore.gridDefinition.value
        return DpOffset(grid.columnWidth * col, grid.rowHeight * row) + when (mode) {
            GridPosition.Mode.TopLeft -> DpOffset.Zero
            GridPosition.Mode.Centroid -> DpOffset(grid.columnWidth / 2, grid.rowHeight / 2)
        }
    }
}