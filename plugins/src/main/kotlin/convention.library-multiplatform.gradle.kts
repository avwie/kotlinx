plugins {
    id("convention.repositories")
    id("convention.publishing")
    kotlin("multiplatform")
}

kotlin {
    js(IR) {
        browser()
    }

    jvm()
}