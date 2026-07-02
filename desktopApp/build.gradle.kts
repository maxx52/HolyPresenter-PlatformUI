plugins {
    kotlin("jvm")
    `maven-publish`
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "org.holypresenter"
version = "0.1.0"

repositories {
    mavenLocal()
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.compose.runtime:runtime:1.11.1")
    implementation("org.jetbrains.compose.foundation:foundation:1.11.1")
    implementation("org.jetbrains.compose.material3:material3:1.9.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "platform-ui"
        }
    }
}