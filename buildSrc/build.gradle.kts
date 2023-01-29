repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("${libs.plugins.kotlin.get().pluginId}:${libs.plugins.kotlin.get().version}")
}