import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "bits.code.vgit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(project(":git"))
    implementation(compose.desktop.currentOs)
    implementation(compose.components.resources)
    implementation("org.eclipse.jgit:org.eclipse.jgit:7.1.0.202411261347-r")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.9.0")
    implementation("com.google.code.gson:gson:2.11.0")
}

task("wrapper", Wrapper::class) {
    gradleVersion = "8.7"
}

compose.desktop {
    application {
        mainClass = "bits.code.vgit.ui.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "vgit"
            packageVersion = "1.0.0"

            macOS {
                iconFile.set(project.file("./src/main/resources/app.svg"))
            }
            windows {
                iconFile.set(project.file("./src/main/resources/app.svg"))
            }
            linux {
                iconFile.set(project.file("./src/main/resources/app.svg"))
            }
        }
    }
}
