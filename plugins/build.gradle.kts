plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.plugin.jetbrains.kotlin)
    implementation(libs.gradle.plugin.jetbrains.serialization)
    implementation(libs.gradle.plugin.jetbrains.compose)
}