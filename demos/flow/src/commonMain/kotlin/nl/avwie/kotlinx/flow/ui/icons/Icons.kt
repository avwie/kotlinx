package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.runtime.Composable
import nl.avwie.kotlinx.flow.state.IconState

@Composable fun Icons(
    icons: List<IconState> = listOf(),
    iconEventHandler: IconEventHandler? = null,
) {
    icons.forEach { element ->
        Icon(
            iconState = element,
            onClick = { iconEventHandler?.onClick(element) },
            onDragStart = { offset -> iconEventHandler?.onDragStart(element, offset) },
            onDrag = { offset -> iconEventHandler?.onDrag(element, offset) },
            onDragEnd = { iconEventHandler?.onDragEnd(element) }
        )
    }
}