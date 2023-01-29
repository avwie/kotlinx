repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("${libs.plugins.kotlin.get().pluginId}:${libs.plugins.kotlin.get().version}")
    implementation("${libs.plugins.serialization.get().pluginId}:${libs.plugins.serialization.get().version}")
    implementation("${libs.plugins.compose.get().pluginId}:${libs.plugins.compose.get().version}")
}