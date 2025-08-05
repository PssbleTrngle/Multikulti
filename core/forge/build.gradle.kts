import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

val kotlin_version = getKotlinPluginVersion()

forge {
    dependOn(project(":core-common"))
//  includesLibrary("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
}
