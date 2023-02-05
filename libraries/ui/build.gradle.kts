plugins {
    id("convention.library-multiplatform")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.jetbrains.coroutines.core)
            }
        }
    }
}