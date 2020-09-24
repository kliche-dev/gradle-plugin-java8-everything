package dev.kliche.plugin.java_version_everything

import org.gradle.api.Plugin
import org.gradle.api.Project

class Java8EverythingPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_ID = "kliche.java8-everything"
    }

    override fun apply(target: Project) {
        target.plugins.apply(AndroidJavaVersionEverythingPlugin::class.java)

        val extension = target.extensions.getByName("javaVersion") as JavaVersionExtension
        extension.version = "1.8"
    }

}
