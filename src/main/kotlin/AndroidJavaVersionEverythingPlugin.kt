package dev.kliche.plugin.java_version_everything

import org.gradle.api.Plugin
import org.gradle.api.Project


class AndroidJavaVersionEverythingPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_ID = "kliche.java-version-everything-android"
    }

    override fun apply(target: Project) {
        target.plugins.apply(JavaVersionEverythingPlugin.PLUGIN_ID)

        val extension = target.extensions.getByName("javaVersion") as JavaVersionExtension

        target.allprojects {
            setupAndroidProjectJavaVersion(it, extension)
            setupKotlinAndroidProjectJavaVersion(it, extension)
        }
    }

    private fun setupAndroidProjectJavaVersion(project: Project, jvmVersion: JavaVersionExtension) {
        project.pluginManager.withPlugin("com.android.application") { project.versionAndroidCompileOptions(jvmVersion) }
        project.pluginManager.withPlugin("com.android.library") { project.versionAndroidCompileOptions(jvmVersion) }
    }

    private fun setupKotlinAndroidProjectJavaVersion(project: Project, jvmVersion: JavaVersionExtension) {
        project.pluginManager.withPlugin("org.jetbrains.kotlin.android") { project.versionKotlinCompileTasks(jvmVersion) }
    }

    private fun Project.versionKotlinCompileTasks(jvmVersion: JavaVersionExtension) {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
            it.kotlinOptions.jvmTarget = jvmVersion.version
        }
    }

    private fun Project.versionAndroidCompileOptions(jvmVersion: JavaVersionExtension) {
        convention.getByType(com.android.build.gradle.BaseExtension::class.java).run {
            compileOptions.setSourceCompatibility(jvmVersion.version)
            compileOptions.setTargetCompatibility(jvmVersion.version)
        }
    }
}
