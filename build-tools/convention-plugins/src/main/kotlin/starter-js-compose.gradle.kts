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

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
    }
}
