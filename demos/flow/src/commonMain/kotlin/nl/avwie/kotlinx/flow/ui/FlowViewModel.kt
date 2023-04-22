package nl.avwie.kotlinx.flow.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import app.cash.molecule.moleculeFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.avwie.kotlinx.flow.observers.ObserveElements
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.ui.ViewModel

class FlowViewModel(
    observeElements: ObserveElements,
) : ViewModel() {
    val state = viewModelScope.launchMolecule (RecompositionClock.Immediate) {
        val elements by observeElements.flow.collectAsState(listOf())
        FlowState(elements)
    }
}