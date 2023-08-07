plugins {
    id("starter.multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.uuid)
                implementation(libs.jetbrains.coroutines.core)
            }
        }
    }
}