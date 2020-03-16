package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldNotBe
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