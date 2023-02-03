import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import nl.avwie.kotlinx.examples.simulation.SimulationModule
import nl.avwie.kotlinx.examples.simulation.SimulationState
import nl.avwie.kotlinx.examples.simulation.SimulationViewModel
import nl.avwie.kotlinx.ui.rememberFrameTimeFLow
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.compose.withDI

fun main() = singleWindowApplication(
    state = WindowState(width = 1280.dp, height = 800.dp)
) {
    withDI(SimulationModule) {
        val frameTimeFlow = rememberFrameTimeFLow()

        val simulationParams = remember {
            SimulationViewModel.Params(
                initialState = SimulationState.random(100, 1280.0, 800.0),
                renderTimer = frameTimeFlow
            )
        }

        val simulationViewModel: SimulationViewModel = viewModel(simulationParams)

        val state by simulationViewModel.state.collectAsState()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.balls.forEach { ball ->
                drawCircle(
                    center = Offset(ball.dynamics.x.dp.toPx(), ball.dynamics.y.dp.toPx()),
                    radius = 10.0f,
                    color = Color(ball.color)
                )
            }
        }
    }
}