plugins {
    id("starter.multiplatform")
}

kotlin {

    js(IR) {
        moduleName = "architecture-demo"
        useCommonJs()

        browser {}
        binaries.library()
        generateTypeScriptDefinitions()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.uuid)
                implementation(libs.jetbrains.coroutines.core)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-core:18.2.0-pre.607")
            }
        }
    }
}