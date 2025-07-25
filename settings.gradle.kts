pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://repo.spongepowered.org/repository/maven-public/") }
        maven { url = uri("https://maven.neoforged.net/releases/") }
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://maven.fabricmc.net/") }
    }
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
