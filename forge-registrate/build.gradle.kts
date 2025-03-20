val mc_version: String by extra
val registrate_forge_version: String by extra

forge {
    enableMixins()

    dependOn(project(":common-core"))
    dependOn(project(":forge-core"))
    dependOn(project(":common-registrate"))
}

dependencies {
    modImplementation("com.tterrag.registrate:Registrate:${registrate_forge_version}")
}