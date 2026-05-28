plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":registrate-common"))
}

dependencies {
    modCompileOnlyApi(libs.registrate.neoforge)

    implementation(project(":core-common"))
    implementation(project(":core-neoforge")) { isTransitive = false }
}
