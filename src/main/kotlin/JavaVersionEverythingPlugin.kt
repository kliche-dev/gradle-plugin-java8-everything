package dev.kliche.plugin.java_version_everything

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

open class JavaVersionExtension {
    var version = "1.8"
}

class JavaVersionEverythingPlugin : Plugin<Project> {
    companion object {
        const val PLUGIN_ID = "kliche.java-version-everything"

        const val REPORT_JAVA_VERSION_TASK = "reportJavaVersion"
    }

    override fun apply(target: Project) {
        val extension = target.extensions.create("javaVersion", JavaVersionExtension::class.java)

        target.allprojects { p ->
            javaVersionEverythingForProject(p, extension)
        }
    }

    private fun javaVersionEverythingForProject(project: Project, javaVersion: JavaVersionExtension) {
        val reportTask = project.tasks.register(REPORT_JAVA_VERSION_TASK) {}

        setupJavaProjectJavaVersion(project, javaVersion)
        setupKotlinProjectJavaVersion(project, javaVersion)

    }

    private fun setupJavaProjectJavaVersion(project: Project, jvmVersion: JavaVersionExtension) {
        project.pluginManager.withPlugin("java") {  project.versionJavaCompileTasks(jvmVersion) }
        project.pluginManager.withPlugin("java-library") {  project.versionJavaCompileTasks(jvmVersion) }
    }

    private fun Project.versionJavaCompileTasks(jvmVersion: JavaVersionExtension) {
        tasks.withType(JavaCompile::class.java) {
            it.sourceCompatibility = jvmVersion.version
            it.targetCompatibility = jvmVersion.version
        }

        try {
            val t = tasks.register("reportJavaCompileJavaVersion") {
                it.doLast {
                    tasks.withType(JavaCompile::class.java).forEach {
                        println("task: $it uses sourceCompatibility ${it.sourceCompatibility}")
                        println("task: $it uses targetCompatibility ${it.targetCompatibility}")
                    }
                }
            }
            tasks.named(REPORT_JAVA_VERSION_TASK).configure{ it.dependsOn(t) }


        } catch (ignored: Throwable ) {}

    }

    private fun setupKotlinProjectJavaVersion(project: Project, jvmVersion: JavaVersionExtension) {
        project.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") { project.versionKotlinCompileTasks(jvmVersion) }
    }

    private fun Project.versionKotlinCompileTasks(jvmVersion: JavaVersionExtension) {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
            it.kotlinOptions.jvmTarget = jvmVersion.version
        }

        val t = tasks.register("reportKotlinCompileJavaVersion") {t ->
            t.doLast {
                tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).forEach {
                    println("task: $it uses kotlinOptions.jvmTarget ${it.kotlinOptions.jvmTarget}")
                }
            }
        }

        tasks.named(REPORT_JAVA_VERSION_TASK) { it.dependsOn(t) }


    }
}
