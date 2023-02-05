plugins {
    id("starter.multiplatform-compose")
    `application`
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:ui"))
                implementation(project(":libraries:ui-compose"))
            }
        }
    }
}

application {
    mainClass.set("AppKt")
}