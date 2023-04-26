package nl.avwie.kotlinx.utils.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import nl.avwie.kotlinx.flow.ui.common.DragEventHandler

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Modifier.onDrag(handler: DragEventHandler): Modifier {
    with (LocalDensity.current) {
        return this@onDrag
            .onDrag(
                onDragStart = { handler.onDragStart(this.toDpOffset(it)) },
                onDrag = { handler.onDrag(this.toDpOffset(it)) },
                onDragEnd = { handler.onDragEnd() }
            )
    }
}