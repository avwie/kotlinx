import gradle.kotlin.dsl.accessors._2ac8a54cb38e450fac76afa89d97da17.compose
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("starter-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {

    js(IR) {
        browser()
        binaries.executable()
    }

    jvm()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(versionCatalog.findLibrary("kodein-di-compose").get())
                implementation(project(":libraries:ui-compose"))
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
                implementation(compose.runtime)
            }
        }
    }
}
