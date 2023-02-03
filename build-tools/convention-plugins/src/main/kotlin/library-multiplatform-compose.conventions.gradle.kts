val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("library-multiplatform.conventions")
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
            }
        }
    }
}