val mc_version: String by extra

plugins {
    id("dev.architectury.loom") version ("1.10-SNAPSHOT")
}

common {
    applyVanillaGradle = false

    dependOn(project(":core-common"))
}

dependencies {
    "minecraft"("com.mojang:minecraft:${mc_version}")
    "mappings"(loom.officialMojangMappings())

    compileOnly("org.ow2.asm:asm-tree:9.5")
}