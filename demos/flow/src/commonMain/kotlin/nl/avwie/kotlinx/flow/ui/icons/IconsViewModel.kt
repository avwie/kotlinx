package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpOffset
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import nl.avwie.kotlinx.flow.interactors.MoveIcon
import nl.avwie.kotlinx.flow.interactors.SelectIcon
import nl.avwie.kotlinx.flow.observers.ObserveIcons
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.ui.ViewModel

class IconsViewModel(
    observeIcons: ObserveIcons,
    private val moveIcon: MoveIcon,
    private val selectIcon: SelectIcon
) : ViewModel() {

    private val observeSelectedIcons = observeIcons.filter { it.selected }

    val state = viewModelScope.launchMolecule (RecompositionClock.Immediate) {
        val icons by observeIcons.flow.collectAsState(listOf())
        icons
    }

    fun select(icon: IconState?) = when {
        icon == null -> selectIcon.deselectAll()
        observeSelectedIcons.value.contains(icon) -> {}
        else -> selectIcon.selectOne(icon)
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
}