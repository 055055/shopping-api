import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

object Version{
    object Test{
        const val KOTEST = "5.1.0"
        const val MOCKK = "1.12.2"
        const val NINJA_SQUAD = "3.1.0"
        const val TESTCONTAINERS = "1.16.3"
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging:2.1.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation ("io.kotest:kotest-assertions-core:${Version.Test.KOTEST}")
    testImplementation ("io.kotest:kotest-runner-junit5:${Version.Test.KOTEST}")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
    testImplementation ("io.mockk:mockk:${Version.Test.MOCKK}")
    testImplementation ("com.ninja-squad:springmockk:${Version.Test.NINJA_SQUAD}")
    testImplementation ("org.testcontainers:testcontainers:${Version.Test.TESTCONTAINERS}")
    testImplementation ("org.testcontainers:mongodb:${Version.Test.TESTCONTAINERS}")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
