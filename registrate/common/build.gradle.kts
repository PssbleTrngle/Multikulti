plugins {
    id("com.possible-triangle.common")
}

common {
    dependOn(project(":core-common"))
}

dependencies {
    modCompileOnlyApi(libs.neoforge.stub)
    modCompileOnly(libs.registrate.neoforge)
}
