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

    //modImplementation(project(":common-core"))
    //modImplementation(project(":common-registrate"))
    modImplementation(project(":forge-core"))
    modImplementation(project(":forge-registrate"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    //dependsOn(tasks.getByName("Data"))
}