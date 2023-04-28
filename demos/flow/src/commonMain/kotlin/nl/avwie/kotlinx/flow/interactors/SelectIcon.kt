package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpRect
import kotlinx.coroutines.flow.*
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore
import nl.avwie.kotlinx.utils.compose.contains

interface SelectIcon {
    fun selectOne(iconState: IconState)
    fun selectMany(iconStates: List<IconState>)

    fun selectRect(rect: DpRect)

    fun deselectOne(iconState: IconState)

    fun deselectMany(iconStates: List<IconState>)

    fun deselectAll()
}

class SelectIconImpl(
    private val iconsStore: IconsStore,
) : SelectIcon {

    override fun selectOne(iconState: IconState) {
        selectMany(listOf(iconState))
    }

    override fun selectMany(iconStates: List<IconState>) {
        deselectAll()
        iconStates.forEach { iconState ->
            iconsStore.updateIcon(iconState) { it.copy(selected = true) }
        }
    }

    override fun selectRect(rect: DpRect) {
        val selectedIcons = iconsStore.icons.value.filter { iconState ->
            rect.contains(iconState.rect)
        }
        selectMany(selectedIcons)
    }

    override fun deselectOne(iconState: IconState) {
        iconsStore.updateIcon(iconState) { it.copy(selected = false) }
    }

    override fun deselectMany(iconStates: List<IconState>) {
        iconStates.forEach { iconState ->
            deselectOne(iconState)
        }
    }

    override fun deselectAll() {
        deselectMany(iconsStore.icons.value)
    }
}