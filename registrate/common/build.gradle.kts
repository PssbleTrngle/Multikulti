plugins {
    id("com.possible-triangle.common")
}

common {
    dependOn(project(":core-common"))
}

dependencies {
    modCompileOnly(libs.neoforge.stub)
    modCompileOnly(libs.registrate.fabric)
}
