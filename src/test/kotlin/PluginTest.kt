package com.github.liminal.kliche.plugin.java8everything

import io.kotlintest.shouldBe
import io.kotlintest.shouldNot
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.WordSpec
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder

class PluginTest : WordSpec( {

    "Using the Plugin ID" should {
        "Apply the plugin" {
            val project = ProjectBuilder.builder().build()
            project.pluginManager.apply("kliche.java8-everything")

            project.plugins.getPlugin(Java8EverythingPlugin::class.java) shouldNotBe null
        }

    }

})