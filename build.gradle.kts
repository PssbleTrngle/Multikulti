import com.possible_triangle.gradle.features.publishing.modifyPublication
import groovy.util.Node

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
            }
        }

        maven {
            url = uri("https://maven.tterrag.com/")
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

    modifyPublication {
        // TODO move to gradle helper
        suppressAllPomMetadataWarnings()

        fun Node.all(key: String) = get(key) as List<Node>
        fun Node.first(key: String) = all(key).first()

        pom.withXml {
            val node = asNode().first("dependencies")
            val dependencies = node.all("dependency")
            dependencies
                .filter { (it.first("groupId").value() == "com.simibubi.create") }
                .forEach { node.remove(it) }
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
