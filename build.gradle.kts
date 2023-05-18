plugins {
    application

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

    implementation(libs.jda)
    implementation(libs.gson)
    implementation(libs.dotenv)
    implementation(libs.logback)

    implementation(libs.configme)

    implementation(libs.coroutines)
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
    shadowJar {
        archiveBaseName.set("${rootProject.name}+${rootProject.version}")

        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dependency.$pkg")
    }

    application {
        mainClass.set("com.ryderbelserion.alexandrite.StarterKt")
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            javaParameters = true
        }
    }
}