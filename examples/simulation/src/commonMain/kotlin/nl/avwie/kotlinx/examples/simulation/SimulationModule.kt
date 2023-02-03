package nl.avwie.kotlinx.examples.simulation

import com.hoc081098.flowext.interval
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.kodein.di.*

sealed interface Timer {
    object Simulation : Timer
}

val SimulationModule = DI {
    bindProvider<Flow<Long>>(Timer.Simulation) {
        interval(initialDelayMillis = 0L, periodMillis = 10L)
            .map { 10L }
            .conflate()
            .shareIn(
                scope = CoroutineScope(Dispatchers.Default),
                started = SharingStarted.Eagerly
            )
    }

    bindFactory { initialState: SimulationState ->
        Simulation(
            initialState = initialState,
            simulationTimer = instance(Timer.Simulation),
            scope = CoroutineScope(Dispatchers.Default)
        )
    }

    bindFactory { params: SimulationViewModel.Params ->
        SimulationViewModel(
            frameTimeFlow = params.renderTimer,
            simulation = instance(arg = params.initialState)
        )
    }
}