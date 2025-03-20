val mc_version: String by extra
val registrate_fabric_version: String by extra

plugins {
    id("dev.architectury.loom") version ("1.7-SNAPSHOT")
}

common {
    applyVanillaGradle = false

    dependOn(project(":common-core"))
}

dependencies {
    "minecraft"("com.mojang:minecraft:${mc_version}")
    "mappings"(loom.officialMojangMappings())

    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}