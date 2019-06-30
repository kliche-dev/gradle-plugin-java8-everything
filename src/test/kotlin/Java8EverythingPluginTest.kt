package com.github.liminal.kliche.plugin.java8everything

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * A simple unit test for the 'baha.greeting' plugin.
 */
class Java8EverythingPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("kliche.java8-everything")

        // Verify the result
//        assertNotNull(project.tasks.findByName("greeting"))
    }
}
