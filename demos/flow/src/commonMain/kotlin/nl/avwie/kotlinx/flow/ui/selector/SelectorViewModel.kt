package nl.avwie.kotlinx.flow.ui.selector

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.interactors.SelectionBox
import nl.avwie.kotlinx.flow.observers.ObserveSelectionBox
import nl.avwie.kotlinx.flow.ui.common.DragEventHandler
import nl.avwie.kotlinx.ui.ViewModel

class SelectorViewModel(
    private val selectionBox: SelectionBox,
    private val observeSelectionBox: ObserveSelectionBox
) : ViewModel(), DragEventHandler {

    val state = observeSelectionBox.selectionBox
    override fun onDragStart(offset: DpOffset) = selectionBox.dragStart(offset)

    override fun onDrag(offset: DpOffset) = selectionBox.drag(offset)

    override fun onDragEnd() = selectionBox.dragEnd()
}