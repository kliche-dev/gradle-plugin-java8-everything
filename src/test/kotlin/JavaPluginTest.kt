package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.gradle.api.tasks.TaskCollection
import org.gradle.api.tasks.compile.JavaCompile

private const val JAVA_PLUGIN_ID = "java"
private const val JAVA_LIBRARY_PLUGIN_ID = "java-library"

private infix fun TaskCollection<JavaCompile>.shouldHaveVersion(version: String) {
    this.forEach { task ->
        task.sourceCompatibility shouldBe version
        task.targetCompatibility shouldBe version
    }
}

class JavaPluginTest : WordSpec( {

    "Using the java Plugin ID" should {

        "Apply the plugin to java project before" {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)
            project.pluginManager.apply(JAVA_PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

        "Apply the plugin to java project after" {
            val project = projectBuilder()
            project.pluginManager.apply(JAVA_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

    }

    "Using the java-library Plugin ID" should {

        "Apply the plugin to java-library project before" {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)
            project.pluginManager.apply(JAVA_LIBRARY_PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

        "Apply the plugin to java-library project after" {
            val project = projectBuilder()
            project.pluginManager.apply(JAVA_LIBRARY_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasksWithType<JavaCompile>() shouldHaveVersion "1.8"
        }

    }



})
