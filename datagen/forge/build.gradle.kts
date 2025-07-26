import org.spongepowered.asm.gradle.plugins.MixinExtension

val mod_id: String by extra
val mc_version: String by extra
val create_forge_version: String by extra

forge {
    enableMixins()

    dependOn(project(":datagen-common"))
}

configure<MixinExtension> {
    config("${mod_id}_datagen.forge.mixins.json")
}

dependencies {
    implementation(project(":core-common"))
    modImplementation(project(":core-forge")) { isTransitive = false }

    modImplementation("com.simibubi.create:create-${mc_version}:${create_forge_version}:slim") { isTransitive = false }
}