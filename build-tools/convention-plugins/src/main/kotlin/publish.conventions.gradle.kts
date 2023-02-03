val spaceUsername: String by project
val spacePassword: String by project

plugins {
    `maven-publish`
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.jetbrains.space/avwie/p/avwie/maven")
            credentials {
                username = spaceUsername
                password = spacePassword
            }
        }
    }
}