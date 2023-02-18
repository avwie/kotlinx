package nl.avwie.kotlinx.demos.simulation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(
    state = WindowState(width = 1280.dp, height = 800.dp)
) {
    Simulation(
        noOfBalls = 100,
        width = 1280.0,
        height = 800.0
    )
}