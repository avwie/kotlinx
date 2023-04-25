package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.utils.compose.toDpOffset
import nl.avwie.kotlinx.utils.compose.toIntOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Icon(
    iconState: IconState,
    onClick: () -> Unit = { },
    onDrag: (DpOffset) -> Unit = { }
) {
    val density = LocalDensity.current

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset { toIntOffset(iconState.position) }
            .onClick { onClick() }
            .onDrag { offset -> onDrag(density.toDpOffset(offset)) }
            .zIndex(if (iconState.selected) 1f else 0f)
    ) {
        drawRect(color = if (iconState.selected) Color.Red else Color.Blue)
    }
}