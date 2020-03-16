package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldNotBe

class PluginTest : WordSpec( {

    "Apply the plugin" should {
        "Using the plugin id" {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            project.getPlugin<Java8EverythingPlugin>() shouldNotBe null
        }

        "Using the class" {
            val project = projectBuilder()
            project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

            project.getPlugin<Java8EverythingPlugin>() shouldNotBe null
        }

    }

})