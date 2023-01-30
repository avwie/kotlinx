val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("starter-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {

    jvm()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs) {
                    exclude(group = "org.jetbrains.compose.material", module = "material")
                    exclude(group = "org.jetbrains.compose.material3", module = "material3")
                }
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(versionCatalog.findLibrary("androidx-compose-compiler").get().get().toString())
}
