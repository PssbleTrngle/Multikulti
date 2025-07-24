val registrate_fabric_version: String by extra

fabric {
    enableMixins()

    dependOn(project(":registrate-common"))
}

dependencies {
    compileOnly(project(":datagen-fabric")) { isTransitive = false }

    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}