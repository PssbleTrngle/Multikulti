val mc_version: String by extra
val create_fabric_version: String by extra

fabric {
    enableMixins()

    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-fabric"))

    modImplementation("com.simibubi.create:create-fabric-${mc_version}:${create_fabric_version}+mc${mc_version}") {
        isTransitive = false
    }
}
