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