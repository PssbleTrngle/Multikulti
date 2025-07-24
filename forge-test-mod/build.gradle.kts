val registrate_forge_version: String by extra

mod {
    id = "test"
}

forge {
    dataGen {
        owner = project
    }
}

dependencies {
    modImplementation("com.tterrag.registrate:Registrate:${registrate_forge_version}")

    implementation(project(":datagen-forge"))
    implementation(project(":registrate-forge"))
}