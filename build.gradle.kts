plugins {
    application

    id("com.github.johnrengelman.shadow") version "8.1.1"

    kotlin("jvm") version "1.9.0"
}

rootProject.version = "0.0.1"
rootProject.description = "A discord bot for my personal server."

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib"))

    implementation("net.dv8tion", "JDA", "5.0.0-beta.12")

    implementation("com.google.code.gson", "gson", "2.10.1")

    implementation("ch.qos.logback", "logback-classic", "1.4.5")

    implementation("io.github.cdimascio", "dotenv-kotlin", "6.4.1")

    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.6.4")
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

        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dep.$pkg")
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