package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import nl.avwie.kotlinx.flow.interactors.MoveIcon
import nl.avwie.kotlinx.flow.observers.ObserveElements
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.ui.ViewModel

class FlowViewModel(
    observeElements: ObserveElements,
    val moveIcon: MoveIcon
) : ViewModel() {

    val state = viewModelScope.launchMolecule (RecompositionClock.Immediate) {
        val elements by observeElements.flow.collectAsState(listOf())
        FlowState(elements)
    }
}