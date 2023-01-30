subprojects {
    group = "nl.avwie.kotlinx"
    version = "1.0.0-SNAPSHOT"
}

plugins {
    @Suppress("DSL_SCOPE_VIOLATION") kotlin("js").version(libs.versions.jetbrains.kotlin).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION") kotlin("jvm").version(libs.versions.jetbrains.kotlin).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION") kotlin("multiplatform").version(libs.versions.jetbrains.kotlin).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION") kotlin("plugin.serialization").version(libs.versions.jetbrains.kotlin).apply(false)
    @Suppress("DSL_SCOPE_VIOLATION") id("org.jetbrains.compose").version(libs.versions.jetbrains.compose).apply(false)
}