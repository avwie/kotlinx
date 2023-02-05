package nl.avwie.kotlinx.demos.simulation

import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.nextULong

data class Color(val red: Int, val green: Int, val blue: Int) {
    private fun Int.hex(): String = this.toString(16).padStart(2, '0')
    fun hex(): String = "#${red.hex()}${green.hex()}${blue.hex()}"
}

data class Dynamics(val x: Double, val y: Double, val dx: Double, val dy: Double)
data class Ball(val dynamics: Dynamics, val radius: Double, val color: Color)
data class Area(val width: Double, val height: Double)


data class SimulationState(val area: Area, val balls: List<Ball>) {

    private fun walls(u: Double, du: Double, limit: Double): Pair<Double, Double> = when {
        u < 0 -> abs(u) to -du
        u > limit -> limit - (u - limit) to -du
        else -> u to du
    }

    fun update(dt: Double) = copy(
        balls = balls.map { ball ->
            val (x0, y0, dx0, dy0) = ball.dynamics
            val (x1, dx1) = walls(x0 + dx0 * dt, dx0, area.width)
            val (y1, dy1) = walls(y0 + dy0 * dt, dy0, area.height)
            ball.copy(
                dynamics = Dynamics(x1, y1, dx1, dy1)
            )
        }
    )

    companion object {
        val EMPTY = SimulationState(Area(0.0, 0.0), listOf())

        fun random(noOfBalls: Int, width: Double, height: Double): SimulationState = SimulationState(
            area = Area(width, height),
            balls = (0 until noOfBalls).map {
                Ball(
                    dynamics = Dynamics(
                        x = Random.nextDouble(width),
                        y = Random.nextDouble(height),
                        dx = Random.nextDouble(-1.0, 1.0) * width / 5000.0,
                        dy = Random.nextDouble(-1.0, 1.0) * height / 5000.0
                    ),
                    radius = Random.nextDouble(minOf(width, height) / 20),
                    color = Color(
                        red = Random.nextInt(256),
                        green = Random.nextInt(256),
                        blue = Random.nextInt(256)
                    )
                )
            }
        )
    }
}
