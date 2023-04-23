package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import nl.avwie.kotlinx.flow.interactors.MoveIcon
import nl.avwie.kotlinx.flow.state.Icon

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Icon(
    icon: Icon,
    moveIcon: MoveIcon,
) {
    val density = LocalDensity.current.density

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(icon.position.first.dp.toPx().toInt(), icon.position.second.dp.toPx().toInt()) }
            .onDrag {
                moveIcon(icon, it, density)
            }
    ) {
        drawRect(color = Color.Blue,)
    }
}