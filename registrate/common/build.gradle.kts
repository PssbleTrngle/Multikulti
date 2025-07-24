val mc_version: String by extra
val registrate_fabric_version: String by extra

plugins {
    id("dev.architectury.loom") version ("1.10-SNAPSHOT")
}

common {
    applyVanillaGradle = false
}

dependencies {
    "minecraft"("com.mojang:minecraft:${mc_version}")
    "mappings"(loom.officialMojangMappings())

    compileOnly(project(":datagen-common")) { isTransitive = false }

    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}