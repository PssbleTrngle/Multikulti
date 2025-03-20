val registrate_fabric_version: String by extra

fabric {
    enableMixins()

    dependOn(project(":common-core"))
    dependOn(project(":fabric-core"))
    dependOn(project(":common-registrate"))
}

dependencies {
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}