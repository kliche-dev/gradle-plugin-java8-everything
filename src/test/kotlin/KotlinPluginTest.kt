package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.gradle.api.tasks.TaskCollection
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private const val KOTLIN_JVM_PLUGIN_ID = "org.jetbrains.kotlin.jvm"

private infix fun TaskCollection<KotlinCompile>.shouldHaveVersion(version: String) {
    this.forEach { task ->
        task.kotlinOptions.jvmTarget shouldBe version
    }
}

class KotlinPluginTest : WordSpec( {

    "Applying plugin to kotlin project" should {

        "applying kotlin plugin first" {
            val project = projectBuilder()

            project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.8"
        }

        "applying kotlin plugin last" {
            val project = projectBuilder()

            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)
            project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)

            project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.8"
        }

        "applying kotlin plugin and then override" {
            val project = projectBuilder()

            project.pluginManager.apply(KOTLIN_JVM_PLUGIN_ID)
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.tasks.withType(KotlinCompile::class.java) {
                kotlinOptions {
                    jvmTarget = "1.7"
                }
            }

            project.tasksWithType<KotlinCompile>() shouldHaveVersion "1.7"
        }

    }

})

