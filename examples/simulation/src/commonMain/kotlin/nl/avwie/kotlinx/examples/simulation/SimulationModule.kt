package nl.avwie.kotlinx.examples.simulation

import androidx.compose.runtime.withFrameMillis
import com.hoc081098.flowext.interval
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

sealed interface Timer {
    object Simulation : Timer
    object Render : Timer
}

val SimulationModule = DI {
    bindProvider(Timer.Simulation) {
        interval(initialDelayMillis = 0L, periodMillis = 10L)
            .map { 10L }
            .conflate()
            .shareIn(
                scope = CoroutineScope(Dispatchers.Default),
                started = SharingStarted.Eagerly
            )
    }

    bindProvider {
        Simulation(
            simulationTimer = instance(Timer.Simulation),
            scope = CoroutineScope(Dispatchers.Default)
        )
    }

    bindProvider {
        SimulationViewModel(
            simulation = instance()
        )
    }
}