import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import nl.avwie.kotlinx.demos.simulation.Simulation

fun main() = singleWindowApplication(
    state = WindowState(width = 1280.dp, height = 800.dp)
) {
    Simulation(
        noOfBalls = 20000,
        width = 1280.0,
        height = 800.0
    ) {
        Box {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                simulation.balls.forEach { ball ->
                    drawCircle(
                        center = Offset(ball.dynamics.x.dp.toPx(), ball.dynamics.y.dp.toPx()),
                        radius = 10.0f.dp.toPx(),
                        color = Color(ball.color.red, ball.color.green, ball.color.blue)
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
                    text = "${benchmark / 1000000.0} ms",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}