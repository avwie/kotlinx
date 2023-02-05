package nl.avwie.kotlinx.demos.simulation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock

class Simulation(
    val initialState: SimulationState,
    val simulationTimer: Flow<Long>,
    val scope: CoroutineScope
) {
    private val _benchmark = MutableStateFlow(0L)
    val benchmark = _benchmark.asStateFlow()

    val state = simulationTimer
        .runningFold(
            initial = initialState
        ) { state, frameTime ->
            val now = Clock.System.now()
            state.update(frameTime.toDouble()).also {
                val elapsed = Clock.System.now() - now
                _benchmark.update { elapsed.inWholeNanoseconds }
            }
        }
        .stateIn(
            scope = scope,
            initialValue = initialState,
            started = SharingStarted.Eagerly
        )
}