package dev.kliche.plugin.java_version_everything

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskCollection
import org.gradle.testfixtures.ProjectBuilder

fun projectBuilder(block: ProjectBuilder.() -> Unit = {}): Project =
    ProjectBuilder.builder().apply(block).build()

inline fun <reified T : Plugin<Project>> Project.getPlugin(): T = this.plugins.getPlugin(T::class.java)
inline fun <reified T : Any> Project.getExtension(): T = this.extensions.getByType(T::class.java)

inline fun <reified T : Task> Project.tasksWithType(): TaskCollection<T> =
    this.tasks.withType(T::class.java)
