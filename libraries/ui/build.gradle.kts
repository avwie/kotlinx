plugins {
    id("library-multiplatform.conventions")
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