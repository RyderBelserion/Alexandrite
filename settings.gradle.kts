dependencyResolutionManagement {
    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://repo.crazycrew.us/api/")
    }
}

rootProject.name = "Alexandrite"