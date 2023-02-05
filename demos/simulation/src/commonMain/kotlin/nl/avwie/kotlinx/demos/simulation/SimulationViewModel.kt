package nl.avwie.kotlinx.demos.simulation

import com.hoc081098.flowext.withLatestFrom
import kotlinx.coroutines.flow.*
import nl.avwie.kotlinx.ui.ViewModel

class SimulationViewModel(
    frameTimeFlow: Flow<Long>,
    simulation: Simulation,
) : ViewModel() {

    data class Params(
        val initialState: SimulationState,
        val renderTimer: Flow<Long>,
    )

    val state = frameTimeFlow
        .withLatestFrom(simulation.state)
        .map { (_, state) ->
            state
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = simulation.initialState
        )
}