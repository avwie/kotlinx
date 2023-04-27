package nl.avwie.kotlinx.flow.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable fun SelectionBox(
    lineLength: Dp = 8.dp,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val topLeft = listOf(Offset(0f, lineLength.toPx()), Offset.Zero, Offset(lineLength.toPx(), 0f))
        val topRight = listOf(Offset(size.width - lineLength.toPx(), 0f), Offset(size.width, 0f), Offset(size.width, lineLength.toPx()))

        val bottomLeft = listOf(Offset(0f, size.height - lineLength.toPx()), Offset(0f, size.height), Offset(lineLength.toPx(), size.height))
        val bottomRight = listOf(Offset(size.width - lineLength.toPx(), size.height), Offset(size.width, size.height), Offset(size.width, size.height - lineLength.toPx()))

        listOf(topLeft, topRight, bottomLeft, bottomRight).forEach { points ->
            drawPoints(
                points = points,
                pointMode = PointMode.Polygon,
                color = Color(0xffafafaf),
                strokeWidth = 1.dp.toPx(),
            )
        }
    }
}