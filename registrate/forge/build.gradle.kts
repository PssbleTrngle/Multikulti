val registrate_forge_version: String by extra

forge {
    enableMixins()

    dependOn(project(":registrate-common"))
}

dependencies {
    modImplementation("com.tterrag.registrate:Registrate:${registrate_forge_version}")

    implementation(project(":core-common"))
    modImplementation(project(":core-forge")) { isTransitive = false }
}