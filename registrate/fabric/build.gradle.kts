val registrate_fabric_version: String by extra

fabric {
    enableMixins()

    dependOn(project(":registrate-common"))
}

dependencies {
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")

    implementation(project(":core-common"))
    modImplementation(project(":core-fabric"))
}

evaluationDependsOn(project(":core-fabric").path)
