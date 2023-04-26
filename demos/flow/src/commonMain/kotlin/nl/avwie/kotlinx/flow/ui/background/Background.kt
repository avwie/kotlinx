package nl.avwie.kotlinx.flow.ui.background

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import nl.avwie.kotlinx.flow.ui.common.DragEventHandler
import nl.avwie.kotlinx.utils.compose.onDrag

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Background(
    backgroundDragEventHandler: DragEventHandler? = null,
    onBackgroundClick: () -> Unit = {}
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .onClick { onBackgroundClick() }
            .then(backgroundDragEventHandler?.let { Modifier.onDrag(it) } ?: Modifier)
    ) {
        drawRect(Color.White)
    }
}