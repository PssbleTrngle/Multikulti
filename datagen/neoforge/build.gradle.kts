import org.spongepowered.asm.gradle.plugins.MixinExtension

val mod_id: String by extra

neoforge {
    enableMixins()

    dependOn(project(":datagen-common"))
}

configure<MixinExtension> {
    config("${mod_id}_datagen.forge.mixins.json")
}

dependencies {
    implementation(project(":core-common"))
    modImplementation(project(":core-neoforge")) { isTransitive = false }
}