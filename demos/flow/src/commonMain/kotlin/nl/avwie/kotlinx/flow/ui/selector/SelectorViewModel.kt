package nl.avwie.kotlinx.flow.ui.selector

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.interactors.Selector
import nl.avwie.kotlinx.flow.observers.ObserveSelector
import nl.avwie.kotlinx.flow.ui.common.DragEventHandler
import nl.avwie.kotlinx.ui.ViewModel

class SelectorViewModel(
    private val selector: Selector,
    private val observeSelector: ObserveSelector
) : ViewModel(), DragEventHandler {

    val state = observeSelector.selectorBox
    override fun onDragStart(offset: DpOffset) = selector.dragStart(offset)

    override fun onDrag(offset: DpOffset) = selector.drag(offset)

    override fun onDragEnd() = selector.dragEnd()
}