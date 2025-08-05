val mod_id: String by extra
val mc_version: String by extra

plugins {
    id("com.possible-triangle.gradle") version ("0.2.17")
}

withKotlin()

val isSnapshot = env["SNAPSHOT"] == "true"
if (isSnapshot) {
    val buildNumber = env["GITHUB_RUN_NUMBER"] ?: "999999"
    mod {
        version = "${mc_version}-${buildNumber}-SNAPSHOT"
    }
}

subprojects {
    repositories {
        modrinthMaven()
        mavenLocal()

        maven {
            url = uri("https://mvn.devos.one/snapshots/")
            content {
                includeGroup("io.github.fabricators_of_create.Porting-Lib")
                includeGroup("com.tterrag.registrate")
            }
        }

        maven {
            url = uri("https://maven.createmod.net")
            content {
                includeGroup("com.simibubi.create")
            }
        }

        maven {
            url = uri("https://mvn.devos.one/snapshots/")
            content {
                includeGroup("com.simibubi.create")
            }
        }

        nexus {
            content {
                includeGroup("com.tterrag.registrate_fabric")
            }
        }
    }

    enablePublishing {
        nexus(snapshot = isSnapshot)
        removePomDependencies(groupId = "com.simibubi.create")
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
