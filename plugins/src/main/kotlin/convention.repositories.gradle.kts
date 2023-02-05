val spaceUsername: String by project
val spacePassword: String by project

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