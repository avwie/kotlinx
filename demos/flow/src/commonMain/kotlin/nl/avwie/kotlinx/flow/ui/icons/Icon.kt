package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import nl.avwie.kotlinx.flow.state.IconState

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Icon(
    iconState: IconState,
    onDragIcon: (Offset) -> Unit = { }
) {
    val density = LocalDensity.current.density

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(iconState.position.x.dp.toPx().toInt(), iconState.position.y.dp.toPx().toInt()) }
            .onDrag { offset ->
                onDragIcon(Offset(offset.x / density, offset.y / density))
            }
            .zIndex(if (iconState.selected) 1f else 0f)
    ) {
        drawRect(color = if (iconState.selected) Color.Red else Color.Blue)
    }
}