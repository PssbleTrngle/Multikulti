fabric {
    enableMixins()

    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    modImplementation(project(":core-fabric"))
}
