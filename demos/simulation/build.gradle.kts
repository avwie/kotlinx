plugins {
    id("starter.multiplatform-compose")
}

kotlin {

    sourceSets {

    }
}

compose.desktop {
    application {
        mainClass = "nl.avwie.kotlinx.demos.simulation.AppKt"
    }
}