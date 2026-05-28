import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":core-common"))
}

dependencies {
    val kotlinVersion = getKotlinPluginVersion()
    apiInclude("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}
