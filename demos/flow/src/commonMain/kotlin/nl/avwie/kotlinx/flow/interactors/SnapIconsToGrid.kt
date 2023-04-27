package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.GridStore

interface SnapIconsToGrid {
    operator fun invoke(icons: List<IconState>)
}

class SnapIconsToGridImpl(
    private val gridStore: GridStore,
    private val moveIcon: MoveIcon
) : SnapIconsToGrid {
    override fun invoke(icons: List<IconState>) {
        val grid = gridStore.gridDefinition.value

        icons.forEach { icon ->
            val gridX = (icon.centroid.x / grid.columnWidth).toInt()
            val gridY = (icon.centroid.y / grid.rowHeight).toInt()
            println("icon ${icon.name} is at $gridX, $gridY")

            val snapX = grid.columnWidth * gridX
            val snapY = grid.rowHeight * gridY

            println("snap to $snapX, $snapY")
            moveIcon.absolute(icon, DpOffset(snapX, snapY))
        }
    }
}