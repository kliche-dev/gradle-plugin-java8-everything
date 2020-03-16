package dev.kliche.plugin.java8everything

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.FeatureExtension
import com.android.build.gradle.LibraryExtension
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

private const val ANDROID_APPLICATION_PLUGIN_ID = "com.android.application"
private const val ANDROID_FEATURE_PLUGIN_ID = "com.android.feature"
private const val ANDROID_LIBRARY_PLUGIN_ID = "com.android.library"

private infix fun <T : BaseExtension> T.shouldHaveVersion(version: JavaVersion) {
    compileOptions.sourceCompatibility shouldBe version
    compileOptions.targetCompatibility shouldBe version
}

class AndroidPluginTest : WordSpec( {
        "Apply to android application project" should {

            "cause plugin to be applied to project" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getPlugin<Java8EverythingPlugin>() shouldNotBe null
            }

            "set source/targetCompatibility of extension to 1.8" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getExtension<AppExtension>() shouldHaveVersion JavaVersion.VERSION_1_8

            }

            "set source/targetCompatibility of extension to 1.8 and then override them to 1.7" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_APPLICATION_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                val extension = project.getExtension<AppExtension>()

                extension.compileOptions.setSourceCompatibility("1.7")
                extension.compileOptions.setTargetCompatibility("1.7")

                extension shouldHaveVersion JavaVersion.VERSION_1_7

            }
        }

        "Apply to android library project" should {

            "cause plugin to be applied to project" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getPlugin<Java8EverythingPlugin>() shouldNotBe null
            }

            "set source/targetCompatibility of extension to 1.8" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getExtension<LibraryExtension>() shouldHaveVersion JavaVersion.VERSION_1_8
            }

            "set source/targetCompatibility of extension to 1.8 and then override them to 1.7" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_LIBRARY_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                val extension = project.getExtension<LibraryExtension>()

                extension.compileOptions.setSourceCompatibility("1.7")
                extension.compileOptions.setTargetCompatibility("1.7")

                extension shouldHaveVersion JavaVersion.VERSION_1_7
            }


        }

        "Apply to android feature project" should {

            "cause plugin to be applied to project" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_FEATURE_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getPlugin<Java8EverythingPlugin>() shouldNotBe null
            }

            "set source/targetCompatibility of extension to 1.8" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_FEATURE_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                project.getExtension<FeatureExtension>() shouldHaveVersion JavaVersion.VERSION_1_8
            }

            "set source/targetCompatibility of extension to 1.8 and then override them to 1.7" {

                val project = projectBuilder()

                project.pluginManager.apply(ANDROID_FEATURE_PLUGIN_ID)
                project.pluginManager.apply(JAVA8_EVERYTHING_PLUGIN)

                val extension = project.getExtension<FeatureExtension>()

                extension.compileOptions.setSourceCompatibility("1.7")
                extension.compileOptions.setTargetCompatibility("1.7")

                extension shouldHaveVersion JavaVersion.VERSION_1_7
            }
        }
})


