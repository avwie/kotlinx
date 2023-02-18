package nl.avwie.kotlinx.demos.simulation

import org.jetbrains.skiko.wasm.onWasmReady


fun main() {
    onWasmReady {
        BrowserViewportWindow("Balls") {
            Simulation(
                noOfBalls = 1000,
                width = 1280.0,
                height = 800.0
            )
        }
    }
}