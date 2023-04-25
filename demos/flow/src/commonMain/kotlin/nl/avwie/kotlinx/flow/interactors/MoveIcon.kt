package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface MoveIcon {
    operator fun invoke(iconState: IconState, offset: DpOffset)
}

class MoveIconImpl(
    private val iconsStore: IconsStore
) : MoveIcon {
    override operator fun invoke(iconState: IconState, offset: DpOffset) {
        iconsStore.updateIcon(iconState) {
            it.copy(position = it.position + offset)
        }
    }
}