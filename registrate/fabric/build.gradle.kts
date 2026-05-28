plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":registrate-common"))
}

dependencies {
    modCompileOnlyApi(libs.registrate.fabric)

    implementation(project(":core-common"))
    implementation(project(":core-fabric"))
}
