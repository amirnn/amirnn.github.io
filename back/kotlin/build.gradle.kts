import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Spring Boot support
    id("org.springframework.boot") version "3.3.0"
    // Pulls dependency versions from Spring’s BOM
    id("io.spring.dependency-management") version "1.1.5"
    // Kotlin/JVM tooling for the project itself
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"   // enables @ConfigurationProperties, etc.
}

group = "com.amirnourinia"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21           // sets Kotlin jvmTarget too

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") // static-file handler
    runtimeOnly("org.springframework.boot:spring-boot-devtools")       // hot-reload (optional)

    // If you want cache-busted URLs later, uncomment Thymeleaf:
    // implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.processResources {
    from("../../front") {            // <— external directory
        into("static")               // ends up inside the JAR as /static/**
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

tasks.test {
    useJUnitPlatform()
}
