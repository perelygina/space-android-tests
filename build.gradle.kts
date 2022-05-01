plugins {
    kotlin("jvm") version "1.6.0"
}

group = "com.jetbrains.space"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.appium:java-client:8.0.0")
    testImplementation ("org.seleniumhq.selenium:selenium-java:4.1.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}