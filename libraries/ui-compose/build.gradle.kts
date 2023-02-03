plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("publish-to-space")
}

kotlin {
    js(IR) {
        browser()
    }
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.coroutines.core)
                implementation(libs.kodein.di.compose)
                implementation(compose.runtime)
                api(project(":libraries:ui"))
            }
        }
    }
}