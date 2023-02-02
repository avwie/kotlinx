package nl.avwie.kotlinx.examples.simulation

import com.hoc081098.flowext.withLatestFrom
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import nl.avwie.kotlinx.ui.ViewModel

class SimulationViewModel(
    simulation: Simulation
) : ViewModel() {

    private val renderTimer =
        MutableSharedFlow<Unit>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

    val state = renderTimer
        .withLatestFrom(simulation.state)
        .map { (_, state) ->
            state
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = SimulationState.EMPTY
        )

    suspend fun tick() {
        renderTimer.emit(Unit)
    }
}