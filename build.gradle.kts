import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

val mod_id: String by extra

plugins {
    id("com.possible-triangle.gradle") version ("0.2.15")
}

val kotlin_version = getKotlinPluginVersion()
mod {
    includedLibraries.add("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
}

withKotlin()

subprojects {
    repositories {
        modrinthMaven()
        mavenLocal()

        maven {
            url = uri("https://mvn.devos.one/snapshots/")
            content {
                includeGroup("com.tterrag.registrate_fabric")
                includeGroup("io.github.fabricators_of_create.Porting-Lib")
            }
        }

        maven {
            url = uri("https://maven.tterrag.com/")
            content {
                includeGroup("com.tterrag.registrate")
            }
        }
    }

    enablePublishing {
        repositories {
            if (env.isCI) nexus()
        }
    }

    val module = project.projectDir.parentFile.name
    mod {
        id = "${mod_id}_${module}"
    }
}

allprojects {
    tasks.withType<Test> { enabled = false }
    tasks.compileTestJava { enabled = false }
    tasks.named("compileTestKotlin") { enabled = false }
}

enableSonarQube()
enableSpotless()
