package nl.avwie.kotlinx.flow.ui.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.IntOffset
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
        drawRect(
            color = Color.Blue.copy(alpha = 0.2f),
            size = rect.size.toSize()
        )
    }
}