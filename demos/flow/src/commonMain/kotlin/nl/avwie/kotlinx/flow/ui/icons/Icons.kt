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
            onClick = { iconEventHandler?.onIconClick(element) },
            onDrag = { offset -> iconEventHandler?.onIconDrag(element, offset) }
        )
    }
}