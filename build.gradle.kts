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
    implementation(project(":ui"))
    implementation(compose.desktop.currentOs)
    implementation(compose.components.resources)
}

compose.desktop {
    application {
        mainClass = "bits.code.vgit.MainKt"

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
