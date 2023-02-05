val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("convention.repositories")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(versionCatalog.findLibrary("jetbrains.coroutines.core").get())
                implementation(versionCatalog.findLibrary("jetbrains.datetime").get())
                implementation(versionCatalog.findLibrary("jetbrains.serialization.json").get())
                implementation(versionCatalog.findLibrary("jetbrains.collections.immutable").get())

                implementation(versionCatalog.findLibrary("uuid").get())
                implementation(versionCatalog.findLibrary("kodein-di-core").get())
                implementation(versionCatalog.findLibrary("flowext").get())

                implementation(project(":libraries:ui"))
            }
        }
    }
}