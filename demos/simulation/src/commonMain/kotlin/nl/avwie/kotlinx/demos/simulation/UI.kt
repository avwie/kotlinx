package nl.avwie.kotlinx.demos.simulation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import nl.avwie.kotlinx.ui.rememberFrameTimeFLow
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.compose.withDI

@Composable
fun Simulation(
    noOfBalls: Int,
    width: Double,
    height: Double,
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

        Box {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                state.simulation.balls.forEach { ball ->
                    drawCircle(
                        center = Offset(ball.dynamics.x.dp.toPx(), ball.dynamics.y.dp.toPx()),
                        radius = 10.0f.dp.toPx(),
                        color = Color(
                            ball.color.red,
                            ball.color.green,
                            ball.color.blue
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(16.dp)
            ) {
                BasicText(
                    text = "${state.benchmark / 1000000.0} ms",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}