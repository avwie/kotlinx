import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.plugin.jetbrains.kotlin)
    implementation(libs.gradle.plugin.jetbrains.serialization)
    implementation(libs.gradle.plugin.jetbrains.compose)
}

tasks.withType<JavaCompile> {
    targetCompatibility = "17"
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}