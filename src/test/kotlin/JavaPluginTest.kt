package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder

class JavaPluginTest : WordSpec( {

    "Using the Plugin ID" should {

        "Apply the plugin to java project before" {
            val project = ProjectBuilder.builder().build()
            project.pluginManager.apply("kliche.java8-everything")
            project.pluginManager.apply("java")

            project.tasks.withType(JavaCompile::class.java).forEach { task ->
                task.sourceCompatibility shouldBe "1.8"
                task.targetCompatibility shouldBe "1.8"
            }
        }
        "Apply the plugin to java project after" {
            val project = ProjectBuilder.builder().build()
            project.pluginManager.apply("java")
            project.pluginManager.apply("kliche.java8-everything")

            project.tasks.withType(JavaCompile::class.java).forEach { task ->
                task.sourceCompatibility shouldBe "1.8"
                task.targetCompatibility shouldBe "1.8"
            }
        }

    }

})