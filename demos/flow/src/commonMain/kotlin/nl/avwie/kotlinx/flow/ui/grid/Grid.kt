package nl.avwie.kotlinx.flow.ui.grid

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import nl.avwie.kotlinx.flow.store.GridStore

@Composable fun Grid(
    grid: GridStore.GridDefinition? = null
) {
    if (grid === null) return

    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val rows = (size.height / grid.rowHeight.toPx()).toInt()
        val cols = (size.width / grid.columnWidth.toPx()).toInt()

        (0 .. rows).forEach { i ->
            drawLine(
                start = Offset(x = 0f, y = i * grid.rowHeight.toPx() + grid.rowHeight.toPx() / 2),
                end = Offset(x = size.width, y = i * grid.rowHeight.toPx() + grid.rowHeight.toPx() / 2),
                color = Color(0xffeeeeee),
                strokeWidth = 1f
            )
        }

        (0 .. rows).forEach { i ->
            drawLine(
                start = Offset(x = 0f, y = i * grid.rowHeight.toPx()),
                end = Offset(x = size.width, y = i * grid.rowHeight.toPx()),
                color = Color(0xffcccccc),
                strokeWidth = 1f
            )
        }

        (0 .. cols).forEach { i ->
            drawLine(
                start = Offset(x = i * grid.columnWidth.toPx() + grid.columnWidth.toPx() / 2, y = 0f),
                end = Offset(x = i * grid.columnWidth.toPx() + grid.columnWidth.toPx() / 2, y = size.height),
                color = Color(0xffeeeeee),
                strokeWidth = 1f
            )
        }

        (0 .. cols).forEach { i ->
            drawLine(
                start = Offset(x = i * grid.columnWidth.toPx(), y = 0f),
                end = Offset(x = i * grid.columnWidth.toPx(), y = size.height),
                color = Color(0xffcccccc),
                strokeWidth = 1f
            )
        }
    }
}