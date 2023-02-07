package nl.avwie.kotlinx.demos.simulation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import nl.avwie.kotlinx.ui.rememberFrameTimeFLow
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.compose.withDI

@Composable
fun Simulation(
    noOfBalls: Int,
    width: Double,
    height: Double,
    simulationScope: @Composable SimulationViewModel.State.() -> Unit = {}
) {
    withDI(SimulationModule) {
        val frameTimeFlow = rememberFrameTimeFLow()

        val simulationParams = remember {
            SimulationViewModel.Params(
                initialState = SimulationState.random(noOfBalls, width, height),
                renderTimer = frameTimeFlow
            )
        }

        val simulationViewModel: SimulationViewModel = viewModel(simulationParams)
        val state by simulationViewModel.state.collectAsState()
        simulationScope(state)
    }
}