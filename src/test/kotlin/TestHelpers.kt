package dev.kliche.plugin.java8everything

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder

val JAVA8_EVERYTHING_PLUGIN = Java8EverythingPlugin::class.java

fun projectBuilder(block: ProjectBuilder.() -> Unit = {}) =
    ProjectBuilder.builder().apply(block).build()

inline fun <reified T : Plugin<Project>> Project.getPlugin() = this.plugins.getPlugin(T::class.java)
inline fun <reified T : Any> Project.getExtension() = this.extensions.getByType(T::class.java)

inline fun <reified T : Task> Project.tasksWithType() =
    this.tasks.withType(T::class.java)