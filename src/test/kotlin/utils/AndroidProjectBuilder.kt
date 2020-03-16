package dev.kliche.plugin.java8everything.utils

import org.gradle.api.Project
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import java.io.PrintWriter
import java.nio.charset.StandardCharsets

data class AndroidTestProject(
    val root: File,
    val project: Project,
    val srcFolder: File
)

fun createAndroidBuildProject(testId: String): AndroidTestProject {
    val root = File("build/tmp/test-app$testId").also {
        if (it.exists() || !it.isDirectory) {
            it.deleteRecursively()
        }
        it.mkdirs()
    }
    val project = TestUtils.createRootProject(root)
    root.resolve("settings.gradle").writeText("")

    val srcFolder = createMainSourceSetFolder(root)
    createAndroidManifest(srcFolder, "com.example.integration_test")

    return AndroidTestProject(root, project, srcFolder)
}

fun createMainSourceSetFolder(target: File): File =
    target.resolve("src").resolve("main").also { it.mkdirs() }

fun createAndroidManifest(target: File, packageName: String) {
    val manifest = File(target, "AndroidManifest.xml").also {
        it.createNewFile()
    }
    PrintWriter(manifest, StandardCharsets.UTF_8.name()).use { writer ->
        writer.print(
            """<manifest package="$packageName"></manifest>"""
        )
    }
}

object TestUtils {
    fun createRootProject(rootDir: File): Project =
        projectBuilder {
            withProjectDir(rootDir)
        }

    fun createChildProject(parent: ProjectInternal, name: String, projectDir: File? = null): Project =
        projectBuilder {
            withName(name)
            withParent(parent)
            withProjectDir(projectDir)
        }

    private fun projectBuilder(block: ProjectBuilder.() -> Unit): Project =
        ProjectBuilder.builder().apply(block).build()

}
