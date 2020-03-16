package dev.kliche.plugin.java8everything

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome


class KotlinPluginTest : WordSpec( {

    "Using the Plugin ID" should {

        "Apply the plugin to kotlin project before" {

            val projectDir = createTempDir().apply { deleteOnExit() }
            val buildGradle = projectDir.resolve("build.gradle")
            buildGradle.writeText("""
                plugins {
                    id "kliche.java8-everything" 
                    id "org.jetbrains.kotlin.jvm"
                }
                
                task verifyTarget {
                    doLast {
                        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).forEach { t ->
                            assert t.kotlinOptions.jvmTarget == "1.8"
                        }
                    }
                }
                
            """.trimIndent())

            val buildResult = GradleRunner.create()
                    .withProjectDir(projectDir)
                    .withPluginClasspath()
                    .withArguments("verifyTarget")
                    .build()


            buildResult.task(":verifyTarget")!!.outcome shouldBe TaskOutcome.SUCCESS
        }

        "Apply the plugin to kotlin project after" {

            val projectDir = createTempDir().apply { deleteOnExit() }
            val buildGradle = projectDir.resolve("build.gradle")
            buildGradle.writeText("""
                plugins {
                    id "org.jetbrains.kotlin.jvm"
                    id "kliche.java8-everything" 
                }
                
                task verifyTarget {
                    doLast {
                        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).forEach { t ->
                            assert t.kotlinOptions.jvmTarget == "1.8"
                        }
                    }
                }
                
            """.trimIndent())

            val buildResult = GradleRunner.create()
                    .withProjectDir(projectDir)
                    .withPluginClasspath()
                    .withArguments("verifyTarget")
                    .build()

            buildResult.task(":verifyTarget")!!.outcome shouldBe TaskOutcome.SUCCESS
        }

    }

})