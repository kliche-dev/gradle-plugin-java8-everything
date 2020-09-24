package dev.kliche.plugin.java_version_everything

import org.gradle.api.tasks.TaskCollection
import org.gradle.api.tasks.compile.JavaCompile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private const val JAVA_PLUGIN_ID = "java"
private const val JAVA_LIBRARY_PLUGIN_ID = "java-library"

private infix fun TaskCollection<JavaCompile>.shouldHaveVersion(version: String) {
    this.forEach { task ->
        assertEquals(task.sourceCompatibility ,version)
        assertEquals(task.targetCompatibility ,version)
    }
}

class JavaPluginTest  {

    @Nested
    @DisplayName("Using the java plugin ID")
    inner class Using_the_java_Plugin_ID {

        @Test
        fun `Apply the plugin to java project before`() {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)
            project.pluginManager.apply(JAVA_PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

        @Test
        fun `Apply the plugin to java project after`() {
            val project = projectBuilder()
            project.pluginManager.apply(JAVA_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }
    }

    @Nested
    @DisplayName("Using the java-library Plugin ID")
    inner class Using_the_java_library_Plugin_ID {

        @Test
        fun `Apply the plugin to java-library project before`() {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)
            project.pluginManager.apply(JAVA_LIBRARY_PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

        @Test
        fun `Apply the plugin to java-library project after`() {
            val project = projectBuilder()
            project.pluginManager.apply(JAVA_LIBRARY_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

    }
}
