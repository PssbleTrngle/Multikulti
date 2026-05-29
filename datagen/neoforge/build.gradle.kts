plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":datagen-common"))

    unitTests = true
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-neoforge")) { isTransitive = false }

    modCompileOnly(variantOf(libs.create.neoforge) { classifier("slim") }) { isTransitive = false }

    testImplementation(libs.bundles.tests)
}
