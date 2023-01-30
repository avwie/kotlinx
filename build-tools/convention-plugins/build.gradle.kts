repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.plugin.jetbrains.kotlin)
    implementation(libs.gradle.plugin.jetbrains.serialization)
    implementation(libs.gradle.plugin.jetbrains.compose)
}