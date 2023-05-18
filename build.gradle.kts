plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"

    kotlin("jvm") version "1.8.21"
}

rootProject.version = "0.0.0.1"
rootProject.description = "A discord bot for my personal server."

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib"))

    implementation("com.google.code.gson:gson:2.10.1")
}

kotlin {
    jvmToolchain(17)

    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }
}