package nl.avwie.kotlinx.flow.interactors

import kotlinx.coroutines.flow.*
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore

interface SelectIcon {
    fun selectOne(iconState: IconState)
    fun selectMany(iconStates: List<IconState>)

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