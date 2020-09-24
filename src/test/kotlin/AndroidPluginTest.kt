package dev.kliche.plugin.java_version_everything

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private const val ANDROID_APPLICATION_PLUGIN_ID = "com.android.application"
private const val ANDROID_LIBRARY_PLUGIN_ID = "com.android.library"

private infix fun <T : BaseExtension> T.shouldHaveVersion(version: JavaVersion) {
    assertEquals(compileOptions.sourceCompatibility, version)
    assertEquals(compileOptions.targetCompatibility, version)
}

class AndroidPluginTest {

    @Nested
    inner class Apply_to_android_application_project {

        @Test
        fun `cause plugin to be applied to project`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            assertNotNull(project.getPlugin<AndroidJavaVersionEverythingPlugin>())
            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }

        @Test
        fun `set source and targetCompatibility of extension to Java 8`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            project.getExtension<AppExtension>() shouldHaveVersion JavaVersion.VERSION_1_8

        }

        @Test
        fun `set source and targetCompatibility of extension to Java 8 and then override them to Java 7`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            val extension = project.getExtension<AppExtension>()

            extension.compileOptions.setSourceCompatibility("1.7")
            extension.compileOptions.setTargetCompatibility("1.7")

            extension shouldHaveVersion JavaVersion.VERSION_1_7

        }
    }


    @Nested
    inner class Apply_to_android_library_project {

        @Test
        fun `cause plugin to be applied to project`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            assertNotNull(project.getPlugin<AndroidJavaVersionEverythingPlugin>())
            assertNotNull(project.getPlugin<JavaVersionEverythingPlugin>())
        }

        @Test
        fun `set source and targetCompatibility of extension to Java 8`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            project.getExtension<LibraryExtension>() shouldHaveVersion JavaVersion.VERSION_1_8
        }

        @Test
        fun `set source and targetCompatibility of extension to Java 8 and then override them to Java 7`() {

            val project = projectBuilder()

            project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
            project.pluginManager.apply(AndroidJavaVersionEverythingPlugin.PLUGIN_ID)

            val extension = project.getExtension<LibraryExtension>()

            extension.compileOptions.setSourceCompatibility("1.7")
            extension.compileOptions.setTargetCompatibility("1.7")

            extension shouldHaveVersion JavaVersion.VERSION_1_7
        }
    }

}


