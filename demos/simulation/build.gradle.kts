plugins {
    id("starter.multiplatform-compose")
}

compose {
    desktop {
        application {
            mainClass = "nl.avwie.kotlinx.demos.simulation.AppKt"
        }
    }
}