package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface MoveIcon {
    operator fun invoke(iconState: IconState, offset: Offset)
}

class MoveIconImpl(
    private val iconsStore: IconsStore
) : MoveIcon {
    override operator fun invoke(iconState: IconState, offset: Offset) {
        iconsStore.updateIcon(iconState) {
            it.copy(position = it.position + offset)
        }
    }
}