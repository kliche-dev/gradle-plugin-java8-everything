package dev.kliche.plugin.java_version_everything

import org.gradle.api.tasks.TaskCollection
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private const val KOTLIN_JVM_PLUGIN_ID = "org.jetbrains.kotlin.jvm"

private infix fun TaskCollection<KotlinCompile>.shouldHaveVersion(version: String) {
    this.forEach { task ->
        assertEquals(task.kotlinOptions.jvmTarget, version)
    }
}

class KotlinPluginTest {

    @Test
    fun `applying kotlin plugin first`() {
        val project = projectBuilder()

        project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)
        project.pluginManager.apply(JavaVersionEverythingPlugin.PLUGIN_ID)

        project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.8"
    }

    @Test
    fun `applying kotlin plugin last`() {
        val project = projectBuilder()

        project.pluginManager.apply(JavaVersionEverythingPlugin.PLUGIN_ID)
        project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)

        project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.8"
    }

    @Test
    fun `applying kotlin plugin and then override`() {
        val project = projectBuilder()

        project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)
        project.pluginManager.apply(JavaVersionEverythingPlugin.PLUGIN_ID)

        project.tasks.withType(KotlinCompile::class.java) {
            it.kotlinOptions.jvmTarget = "1.7"
        }

        project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.7"
    }

}

