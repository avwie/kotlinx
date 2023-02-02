package nl.avwie.kotlinx.examples.simulation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

class Simulation(
    val simulationTimer: Flow<Long>,
    val scope: CoroutineScope
) {
    private val initialState = SimulationState.random(20000, 500.0, 500.0)

    val state = simulationTimer
        .runningFold(
            initial = initialState
        ) { state, frameTime ->
            state.update(frameTime.toDouble())
        }
        .stateIn(
            scope = scope,
            initialValue = initialState,
            started = SharingStarted.Eagerly
        )
}