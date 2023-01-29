plugins {
    kotlin("multiplatform")
    id("publish-to-space")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
    }
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kodein.di.compose)
                implementation(compose.runtime)
                api(project(":libraries:ui"))
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.androidx.compose.compiler.get().toString())
}