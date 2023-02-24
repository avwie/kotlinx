package nl.avwie.kotlinx.demos.simulation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
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

        val screenshotState = remember { ScreenshotState() }
        val latestImage by screenshotState.latestScreenShot.collectAsState()

        LaunchedEffect(Unit) {
            while (true) {
                screenshotState.takeScreenshot()
                delay(1000 / 15)
            }
        }

        ScreenshotContainer(
            screenshotState = screenshotState,
            targetDensity = Density(0.1f)
        ) {
            Box(
                modifier = Modifier
            ) {
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

        if (latestImage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                with(LocalDensity.current) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(fraction = 0.33f)
                            .align(alignment = Alignment.BottomEnd)
                            .border(width = 2.dp, color = Color.Red)
                    ) {
                        Image(
                            bitmap = latestImage!!,
                            contentDescription = "Screenshot",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}