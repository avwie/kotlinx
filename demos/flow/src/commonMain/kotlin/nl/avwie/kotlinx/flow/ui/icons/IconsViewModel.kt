package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import kotlinx.coroutines.launch
import nl.avwie.kotlinx.flow.interactors.DragIcon
import nl.avwie.kotlinx.flow.interactors.SelectIcon
import nl.avwie.kotlinx.flow.observers.ObserveIcons
import nl.avwie.kotlinx.flow.observers.ObserveSelector
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.SelectorStore
import nl.avwie.kotlinx.ui.ViewModel

class IconsViewModel(
    observeIcons: ObserveIcons,
    observeSelector: ObserveSelector,
    private val selectIcon: SelectIcon,
    private val dragIcon: DragIcon
) : ViewModel(), IconEventHandler {

    val state = observeIcons.flow

    private val selectedIcons = observeIcons.filter { it.selected }

    init {
        viewModelScope.launch {
            observeSelector.selectorEvents.collect { event ->
                when (event) {
                    is SelectorStore.Event.Finished -> {
                        selectBox(event.rect)
                    }
                }
            }
        }
    }

    override fun onClick(target: IconState) {
        selectIcon.selectOne(target)
    }

    override fun onDragStart(target: IconState, offset: DpOffset) {
        when {
            selectedIcons.value.contains(target) -> {
                selectedIcons.value.forEach { iconState ->
                    dragIcon.start(iconState)
                }
            }
            else -> {
                selectIcon.selectOne(target)
                dragIcon.start(target)
            }
        }
    }

    override fun onDrag(target: IconState, offset: DpOffset) {
        selectedIcons.value.forEach { iconState ->
            dragIcon.drag(iconState, offset)
        }
    }

    override fun onDragEnd(target: IconState) {
        selectedIcons.value.forEach { iconState ->
            dragIcon.end(iconState, mode = DragIcon.Mode.SnapToGrid(snapBack = true))
        }
    }

    fun selectBox(rect: DpRect) {
        selectIcon.selectRect(rect)
    }

    fun deselect() {
        selectIcon.deselectAll()
    }
}