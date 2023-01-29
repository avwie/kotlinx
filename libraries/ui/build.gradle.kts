plugins {
    kotlin("multiplatform")
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
            }
        }
    }
}