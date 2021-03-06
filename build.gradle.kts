plugins {
    kotlin("jvm") version "1.6.0"
    id("io.qameta.allure") version "2.9.6"
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
    implementation("io.appium:java-client:8.0.0") {
        exclude("org.seleniumhq.selenium", "selenium-java")
    }
    implementation("org.seleniumhq.selenium:selenium-java:4.1.4")
    implementation("commons-io:commons-io:2.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}