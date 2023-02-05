package nl.avwie.kotlinx.demos.simulation

import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.renderComposableInBody
import org.jetbrains.compose.web.svg.*

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