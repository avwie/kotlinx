package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface MoveIcon {
    fun absolute(iconState: IconState, position: DpOffset)
    fun relative(iconState: IconState, offset: DpOffset)
}

class MoveIconImpl(
    private val iconsStore: IconsStore
) : MoveIcon {

    override fun absolute(iconState: IconState, position: DpOffset) {
        iconsStore.updateIcon(iconState) {
            it.copy(position = position)
        }
    }
    override fun relative(iconState: IconState, offset: DpOffset) {
        iconsStore.updateIcon(iconState) {
            it.copy(position = it.position + offset)
        }
    }
}