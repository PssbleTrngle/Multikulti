val mod_id: String by extra

neoforge {
    enableMixins()

    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    modImplementation(project(":core-neoforge")) { isTransitive = false }
}