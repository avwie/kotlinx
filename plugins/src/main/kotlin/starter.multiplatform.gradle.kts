val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("convention.repositories")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    js(IR) {
        generateTypeScriptDefinitions()
    }

    jvm {
        withJava()
    }
}