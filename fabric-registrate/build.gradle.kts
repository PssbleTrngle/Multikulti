val registrate_fabric_version: String by extra
val mod_id: String by extra

mod {
    id = "$mod_id-registrate"
}

fabric {
    enableMixins()

    dependOn(project(":common-registrate"))
}

dependencies {
    compileOnly(project(":fabric-core")) { isTransitive = false }

    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}