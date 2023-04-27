package nl.avwie.kotlinx.flow.ui.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.size
import androidx.compose.ui.zIndex

@Composable fun Selector(
    rect: DpRect? = null
) {
    if (rect === null) return

    Canvas(
        modifier = Modifier
            .size(rect.size)
            .offset { IntOffset(x = rect.left.toPx().toInt(), y = rect.top.toPx().toInt()) }
            .zIndex(2f)
    ) {
        drawRoundRect(
            color = Color.Blue.copy(alpha = 0.05f),
            cornerRadius = CornerRadius(4.dp.toPx()),
        )

        drawRoundRect(
            color = Color.Blue.copy(alpha = 0.2f),
            cornerRadius = CornerRadius(4.dp.toPx()),
            style = Stroke(
                width = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(4.dp.toPx(), 4.dp.toPx()))
            )
        )
    }
}