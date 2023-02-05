plugins {
    id("convention.library-multiplatform-compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.coroutines.core)
                implementation(libs.kodein.di.core)
                implementation(libs.kodein.di.compose)
                api(project(":libraries:ui"))
            }
        }
    }
}