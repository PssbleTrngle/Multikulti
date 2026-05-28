plugins {
    id("com.possible-triangle.core")
    id("com.possible-triangle.common") apply false
    id("com.possible-triangle.neoforge") apply false
    id("com.possible-triangle.fabric") apply false
}

withKotlin()

subprojects {
    apply(plugin = "com.possible-triangle.core")

    repositories {
        nexus {
            content {
                includeGroup("com.possible-triangle")
                includeGroup("com.tterrag.registrate_fabric")
                includeGroup("io.github.fabricators_of_create.Porting-Lib")
            }
        }

        maven {
            url = uri("https://maven.gegy.dev/releases/")
            content {
                includeGroup("com.tterrag.registrate")
            }
        }

        maven {
            url = uri("https://maven.createmod.net")
            content {
                includeGroup("com.simibubi.create")
            }
        }
    }

    val module = project.projectDir.parentFile.name
    val baseId = providers.gradleProperty("mod_id")
    mod.id = baseId.map { "${it}_$module" }
    base.archivesName = baseId.map { "$it-${project.name}-${mod.version.get()}" }

    upload {
        maven {
            name = baseId.map { "$it-${project.name}" }
            nexus()
        }
    }
}

enableSonarQube()
enableSpotless()
