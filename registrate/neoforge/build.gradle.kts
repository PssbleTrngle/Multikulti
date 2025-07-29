val registrate_neoforge_version: String by extra

neoforge {
    enableMixins()

    dependOn(project(":registrate-common"))
}

dependencies {
    modImplementation("com.tterrag.registrate:Registrate:${registrate_neoforge_version}")

    implementation(project(":core-common"))
    modImplementation(project(":core-neoforge")) { isTransitive = false }
}