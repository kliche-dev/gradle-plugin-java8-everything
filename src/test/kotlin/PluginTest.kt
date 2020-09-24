package dev.kliche.plugin.java_version_everything

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PluginTest {

    @Nested
    @DisplayName("JavaVersionEverything plugin")
    inner class JavaVersionEverythingPluginTest {

        @Test
        fun `Apply the plugin using the plugin id`() {
            val project = projectBuilder()
            project.pluginManager.apply(JavaVersionEverythingPlugin.PLUGIN_ID)

            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }

        @Test
        fun `Apply the plugin using the class`() {
            val project = projectBuilder()
            project.pluginManager.apply(JavaVersionEverythingPlugin::class.java)

            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }
    }

    @Nested
    @DisplayName("JavaVersionEverythingAndroid plugin")
    inner class JavaEverythingAndroidPluginTest {

        @Test
        fun `Apply the plugin using the plugin id`() {
            val project = projectBuilder()
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            assertNotNull(project.getPlugin<AndroidJavaVersionEverythingPlugin>())
            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }

        @Test
        fun `Apply the plugin using the class`() {
            val project = projectBuilder()
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin::class.java)

            assertNotNull(project.getPlugin<AndroidJavaVersionEverythingPlugin>())
            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }
    }

    @Nested
    @DisplayName("Java8Everything legacy plugin")
    inner class Java8EverythingLegacyPluginTest {

        @Test
        fun `Apply the plugin using the plugin id`() {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin.PLUGIN_ID)

            assertNotNull(project.getPlugin<Java8EverythingPlugin>())
        }

        @Test
        fun `Apply the plugin using the class`() {
            val project = projectBuilder()
            project.pluginManager.apply(Java8EverythingPlugin::class.java)

            assertNotNull(project.getPlugin<Java8EverythingPlugin>())
        }
    }

}
