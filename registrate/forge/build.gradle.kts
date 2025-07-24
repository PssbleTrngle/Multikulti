val registrate_forge_version: String by extra

forge {
    enableMixins()

    dependOn(project(":registrate-common"))
}

dependencies {
    compileOnly(project(":datagen-forge")) { isTransitive = false }

    modImplementation("com.tterrag.registrate:Registrate:${registrate_forge_version}")
}