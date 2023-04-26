package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.store.SelectorStore

interface SelectionBox {
    fun dragStart(offset: DpOffset)
    fun drag(offset: DpOffset)

    fun dragEnd()
}

class SelectionBoxImpl(
    private val selectorStore: SelectorStore
) : SelectionBox {
    override fun dragStart(offset: DpOffset) = selectorStore.dragStart(offset)

    override fun drag(offset: DpOffset) = selectorStore.drag(offset)

    override fun dragEnd() = selectorStore.dragEnd()
}