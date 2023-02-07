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

    data class State(
        val simulation: SimulationState,
        val benchmark: Long
    )

    val state = frameTimeFlow
        .withLatestFrom(simulation.state)
        .map { (_, state) ->
            State(
                simulation = state,
                benchmark = simulation.benchmark.value
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = State(simulation.initialState, 0L)
        )
}