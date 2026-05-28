plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-fabric"))
}
