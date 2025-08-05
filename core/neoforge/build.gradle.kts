import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

val kotlin_version = getKotlinPluginVersion()

neoforge {
    dependOn(project(":core-common"))
//  includesLibrary("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
}
