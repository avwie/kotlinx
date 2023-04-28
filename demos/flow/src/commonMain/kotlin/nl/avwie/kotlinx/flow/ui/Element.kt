package nl.avwie.kotlinx.flow.ui

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
import nl.avwie.kotlinx.flow.interactors.MoveElement
import nl.avwie.kotlinx.flow.state.Element
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Element(
    element: Element,
    moveElement: MoveElement,
) {
    val density = LocalDensity.current.density

    Canvas(
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(element.position.first.dp.toPx().toInt(), element.position.second.dp.toPx().toInt()) }
            .onDrag {
                println("Dragged ${it}")
                moveElement(element, it, density)
            }
    ) {
        drawRect(color = Color.Blue,)
    }
}