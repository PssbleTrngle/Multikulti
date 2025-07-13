plugins {
    id("com.possible-triangle.gradle") version ("0.0.0-dev")
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
        githubPackages()
        repositories {
            mavenLocal()
        }
    }
}

enableSonarQube()
enableSpotless()