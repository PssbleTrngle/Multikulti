plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":datagen-common"))
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-neoforge")) { isTransitive = false }

    modImplementation(variantOf(libs.create.neoforge) { classifier("slim") }) { isTransitive = false }
}
