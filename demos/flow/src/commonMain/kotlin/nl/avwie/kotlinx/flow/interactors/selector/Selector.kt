package nl.avwie.kotlinx.flow.interactors.selector

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.store.SelectorStore

interface Selector {
    fun dragStart(offset: DpOffset)
    fun drag(offset: DpOffset)

    fun dragEnd()
}

class SelectorImpl(
    private val selectorStore: SelectorStore
) : Selector {
    override fun dragStart(offset: DpOffset) = selectorStore.dragStart(offset)

    override fun drag(offset: DpOffset) = selectorStore.drag(offset)

    override fun dragEnd() = selectorStore.dragEnd()
}