package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import com.benasher44.uuid.Uuid
import kotlinx.coroutines.launch
import nl.avwie.kotlinx.flow.interactors.grid.SnapToGrid
import nl.avwie.kotlinx.flow.interactors.icons.MoveIcon
import nl.avwie.kotlinx.flow.interactors.icons.SelectIcon
import nl.avwie.kotlinx.flow.observers.ObserveIcons
import nl.avwie.kotlinx.flow.observers.ObserveSelector
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.SelectorStore
import nl.avwie.kotlinx.ui.ViewModel

class IconsViewModel(
    observeIcons: ObserveIcons,
    observeSelector: ObserveSelector,
    private val selectIcon: SelectIcon,
    private val moveIcon: MoveIcon,
    private val snapToGrid: SnapToGrid
) : ViewModel(), IconEventHandler {

    val state = observeIcons.flow

    private val startingPositions = mutableMapOf<Uuid, DpOffset>()

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
                    startingPositions[iconState.id] = iconState.position
                }
            }
            else -> {
                selectIcon.selectOne(target)
                startingPositions[target.id] = target.position
            }
        }
    }

    override fun onDrag(target: IconState, offset: DpOffset) {
        selectedIcons.value.forEach { iconState ->
            moveIcon.relative(iconState, offset)
        }
    }

    override fun onDragEnd(target: IconState) {
        selectedIcons.value.forEach { iconState ->
            snapToGrid.snapToGrid(
                iconState = iconState,
                mode = SnapToGrid.Mode.SnapBack(startingPositions[iconState.id]!!)
            )
            startingPositions.remove(iconState.id)
        }
    }

    fun selectBox(rect: DpRect) {
        selectIcon.selectRect(rect)
    }

    fun deselect() {
        selectIcon.deselectAll()
    }
}