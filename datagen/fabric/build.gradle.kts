fabric {
    enableMixins()

    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-fabric"))
}
