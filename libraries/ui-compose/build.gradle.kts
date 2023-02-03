plugins {
    id("library-multiplatform-compose.conventions")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.coroutines.core)
                implementation(libs.kodein.di.core)
                api(project(":libraries:ui"))
            }
        }
    }
}