val mc_version: String by extra
val create_forge_version: String by extra

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

    modImplementation("com.simibubi.create:create-${mc_version}:${create_forge_version}:slim") { isTransitive = false }

    compileOnly("org.ow2.asm:asm-tree:9.5")
}