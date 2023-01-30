rootProject.name = "kotlinx"

dependencyResolutionManagement {
    val spaceUsername: String by settings
    val spacePassword: String by settings

    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

        maven {
            url = uri("https://maven.pkg.jetbrains.space/avwie/p/avwie/maven")
            credentials {
                username = spaceUsername
                password = spacePassword
            }
        }
    }
}

includeBuild("build-tools/convention-plugins")

include(":libraries:ui")
include(":libraries:ui-compose")
include(":examples:simulation")