import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}
kotlin {
    jvmToolchain(17)
}