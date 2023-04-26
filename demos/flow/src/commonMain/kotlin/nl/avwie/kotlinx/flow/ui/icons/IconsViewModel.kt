package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.launch
import nl.avwie.kotlinx.flow.interactors.MoveIcon
import nl.avwie.kotlinx.flow.interactors.SelectIcon
import nl.avwie.kotlinx.flow.observers.ObserveIcons
import nl.avwie.kotlinx.flow.observers.ObserveSelectionBox
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.SelectorStore
import nl.avwie.kotlinx.ui.ViewModel

class IconsViewModel(
    observeIcons: ObserveIcons,
    observeSelectionBox: ObserveSelectionBox,
    private val moveIcon: MoveIcon,
    private val selectIcon: SelectIcon
) : ViewModel(), IconEventHandler {

    private val observeSelectedIcons = observeIcons.filter { it.selected }

    val state = viewModelScope.launchMolecule (RecompositionClock.Immediate) {
        val icons by observeIcons.flow.collectAsState(listOf())
        icons
    }

    init {
        viewModelScope.launch {
            observeSelectionBox.selectionEvents.collect { event ->
                when (event) {
                    is SelectorStore.Event.Finished -> {
                        selectBox(event.rect)
                    }
                }
            }
        }
    }

    fun select(icon: IconState?) = when {
        icon == null -> selectIcon.deselectAll()
        observeSelectedIcons.value.contains(icon) -> {}
        else -> selectIcon.selectOne(icon)
    }

    fun selectMany(icons: List<IconState>) {
        selectIcon.selectMany(icons)
    }

    fun selectBox(rect: DpRect) {
        val selected = state.value.filter { icon ->
            icon.position.x >= rect.left &&
                    icon.position.x + icon.size.width <= rect.right &&
                    icon.position.y >= rect.top &&
                    icon.position.y + icon.size.height <= rect.bottom
        }

        selectMany(selected)
    }

    fun deselect() {
        selectIcon.deselectAll()
    }

    fun drag(icon: IconState, offset: DpOffset) {
        if (observeSelectedIcons.value.contains(icon)) {
            observeSelectedIcons.value.forEach { moveIcon(it, offset) }
        } else {
            selectIcon.selectOne(icon)
            moveIcon(icon, offset)
        }
    }

    override fun onIconClick(icon: IconState) {
        select(icon)
    }

    override fun onIconDrag(icon: IconState, offset: DpOffset) {
        drag(icon, offset)
    }
}