package dev.kliche.plugin.java8everything

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class Java8EverythingPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_ID = "kliche.java8-everything"
        private const val JAVA_8 = "1.8"
    }

    override fun apply(target: Project) {
        target.allprojects { java8Everything() }
    }

    private fun Project.java8Everything() {
        // Setup all java modules to use Java 8
        pluginManager.withPlugin("java") {  java8javaCompileTasks() }
        pluginManager.withPlugin("java-library") {  java8javaCompileTasks() }

        // Setup all kotlin modules to use Java 8
        pluginManager.withPlugin("org.jetbrains.kotlin.jvm") { java8kotlinCompileTasks() }
        pluginManager.withPlugin("org.jetbrains.kotlin.android") { java8kotlinCompileTasks() }

        // Setup all android modules to use Java 8
        pluginManager.withPlugin("com.android.application") { java8android() }
        pluginManager.withPlugin("com.android.library") { java8android() }
        pluginManager.withPlugin("com.android.feature") { java8android() }
        pluginManager.withPlugin("com.android.instantapp") { java8android() }
    }

    private fun Project.java8javaCompileTasks() {
        tasks.withType<JavaCompile> {
            sourceCompatibility = JAVA_8
            targetCompatibility = JAVA_8
        }
    }

    private fun Project.java8kotlinCompileTasks() {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = JAVA_8
        }
    }

    private fun Project.java8android() {
        configure<com.android.build.gradle.BaseExtension> {
            compileOptions {
                setSourceCompatibility(JAVA_8)
                setTargetCompatibility(JAVA_8)
            }
        }
    }

}
