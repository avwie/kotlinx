package nl.avwie.kotlinx.demos.simulation

import nl.avwie.kotlinx.ui.SkiaCanvas
import org.jetbrains.skiko.wasm.onWasmReady


fun main() {
    onWasmReady {
        SkiaCanvas(title = "Bouncy Balls") {
            Simulation(
                noOfBalls = 1000,
                width = 1280.0,
                height = 800.0
            )
        }
    }
}