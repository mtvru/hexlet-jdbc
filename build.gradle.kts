plugins {
    id("application")
    id("se.patrikerdes.use-latest-versions") version "0.2.19"
    id("com.github.ben-manes.versions") version "0.53.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "io.hexlet.Application"
}

dependencies {
    implementation("com.h2database:h2:2.2.220")
}
