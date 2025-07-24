val mod_id: String by extra
val registrate_forge_version: String by extra

mod {
    id = "${mod_id}_registrate"
}

forge {
    enableMixins()

    dependOn(project(":common-registrate"))
}

dependencies {
    compileOnly(project(":forge-core")) { isTransitive = false }

    modImplementation("com.tterrag.registrate:Registrate:${registrate_forge_version}")
}