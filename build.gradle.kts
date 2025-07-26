val mod_id: String by extra

plugins {
    id("com.possible-triangle.gradle") version ("0.2.16")
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
