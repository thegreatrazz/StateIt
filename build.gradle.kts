plugins {
    kotlin("jvm") version "1.4.0"
}

kotlin {}

sourceSets.main {
    java.srcDir("src")
}

repositories {
    jcenter()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "nz.thegreatrazz.stateit.ui.MainWindow"
    }
}
