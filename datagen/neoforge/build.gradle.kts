val mc_version: String by extra
val create_neoforge_version: String by extra

neoforge {
    enableMixins()

    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    modImplementation(project(":core-neoforge")) { isTransitive = false }

    modImplementation("com.simibubi.create:create-${mc_version}:${create_neoforge_version}:slim") { isTransitive = false }
}