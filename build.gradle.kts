plugins {
    id("com.possible-triangle.gradle") version ("0.2.5")
    id("com.diffplug.spotless") version ("7.0.2")
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

spotless {
    kotlin {
        ktlint()

        leadingTabsToSpaces()

        suppressLintsFor {
            shortCode = "standard:package-name"
        }
    }

    java {
        importOrder()
        removeUnusedImports()

        leadingTabsToSpaces()
    }

    kotlinGradle {
        ktlint()

        suppressLintsFor {
            shortCode = "standard:property-naming"
        }
    }
}