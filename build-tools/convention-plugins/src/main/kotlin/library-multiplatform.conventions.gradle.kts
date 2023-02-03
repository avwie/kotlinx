plugins {
    id("repositories.conventions")
    id("publish.conventions")
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser()
    }

    jvm()
}