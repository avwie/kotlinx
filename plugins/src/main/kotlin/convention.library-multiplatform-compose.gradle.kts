val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("convention.library-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
    }

    jvm()

    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs) {
                    exclude(group = "org.jetbrains.compose.material", module = "material")
                    exclude(group = "org.jetbrains.compose.material3", module = "material3")
                }
                implementation(versionCatalog.findLibrary("jetbrains.coroutines.swing").get())
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
            }
        }
    }
}