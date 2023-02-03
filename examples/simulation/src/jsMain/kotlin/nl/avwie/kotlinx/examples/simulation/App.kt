package nl.avwie.kotlinx.examples.simulation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import nl.avwie.kotlinx.ui.rememberFrameTimeFLow
import nl.avwie.kotlinx.ui.viewModel
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.dom.Canvas
import org.jetbrains.compose.web.renderComposableInBody
import org.jetbrains.compose.web.svg.*
import org.kodein.di.compose.withDI

@OptIn(ExperimentalComposeWebSvgApi::class)
fun main() {
    renderComposableInBody {
        Simulation(
            noOfBalls = 500,
            width = 1280.0,
            height = 800.0
        ) {
            Svg(
                viewBox = "0 0 1280 800",
                attrs = {
                    width(1280.0)
                    height(800.0)
                }
            ) {
                balls.forEach { ball ->
                    Circle(
                        cx = ball.dynamics.x,
                        cy = ball.dynamics.y,
                        r = 10.0f,
                        attrs = {
                            fill(ball.color.hex())
                        }
                    )
                }
            }
        }
    }
}