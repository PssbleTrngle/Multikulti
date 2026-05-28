pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.possible-triangle.helper") version ("1.4")
}

fun module(name: String) {
    listOf("common", "neoforge", "fabric").forEach { platform ->
        include(":$name-$platform")
        project(":$name-$platform").projectDir = file("$name/$platform")
    }
}

module("core")
module("datagen")
module("registrate")
// include(":forge-test-mod")
