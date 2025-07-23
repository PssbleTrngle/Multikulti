val mod_id: String by extra
val mc_version: String by extra
val registrate_fabric_version: String by extra

mod {
    id = "$mod_id-registrate"
}

plugins {
    id("dev.architectury.loom") version ("1.10-SNAPSHOT")
}

common {
    applyVanillaGradle = false
}

dependencies {
    "minecraft"("com.mojang:minecraft:${mc_version}")
    "mappings"(loom.officialMojangMappings())

    compileOnly(project(":common-core")) { isTransitive = false }

    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}