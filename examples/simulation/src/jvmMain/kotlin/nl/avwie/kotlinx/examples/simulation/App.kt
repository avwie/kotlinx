import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.launch
import nl.avwie.kotlinx.examples.simulation.SimulationModule
import nl.avwie.kotlinx.examples.simulation.SimulationViewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

fun main() = singleWindowApplication(
    state = WindowState(width = 500.dp, height = 500.dp)
) {
    withDI(SimulationModule) {
        val simulationViewModel: SimulationViewModel by rememberInstance()
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

        LaunchedEffect(Unit) {
            while (true) {
                withFrameMillis {
                    launch {
                        simulationViewModel.tick()
                    }
                }
            }
        }
    }
}