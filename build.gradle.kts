plugins {
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.dokka") version "1.4.0-rc"
}

kotlin {

}

sourceSets.main {
    java.srcDir("src")
}

repositories {
    jcenter() // or maven(url="https://dl.bintray.com/kotlin/dokka")
}

tasks.dokkaHtml {
    outputDirectory = "$buildDir/docs/kotlin"
}

tasks.dokkaJavadoc {
    outputDirectory = "$buildDir/docs/javadoc"
}
