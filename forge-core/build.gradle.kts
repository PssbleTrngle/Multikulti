forge {
    enableMixins()

    dependOn(project(":common-core"))
}

// val jarJar = the<JarJarProjectExtension>()

dependencies {
    // compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:${mixin_extras_version}")!!)
    // implementation("jarJar"("io.github.llamalad7:mixinextras-forge:${mixin_extras_version}")) {
    //     jarJar.ranged(this, "[${mixin_extras_version},)")
    // }
}