plugins {
    kotlin("jvm")
    `maven-publish`
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "org.holypresenter"
version = "0.7.1"

repositories {
    mavenLocal()
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.compose.runtime:runtime:1.11.1")
    implementation("org.jetbrains.compose.foundation:foundation:1.11.1")
    implementation(libs.androidx.material3.desktop)
    implementation("org.holypresenter:platform-api:0.5.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "platform-ui"
        }
    }
}