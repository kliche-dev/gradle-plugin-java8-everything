plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.10.1"
}

version = "0.8.0"
description = "Plugin for automatically setting source/targetCompatibility of all modules in project to Java 8"

repositories {
    google()
    mavenCentral()
}

configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("java8Everything") {
            id = "kliche.java8-everything"
            displayName = "Java 8 Everything Plugin"
            description = project.description
            implementationClass = "com.github.liminal.kliche.plugin.java8everything.Java8EverythingPlugin"
        }
    }
}

// Ignore warning about using experimental features
kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

// THIS! This is what solved the test problems.
tasks.withType<PluginUnderTestMetadata>().configureEach {
    pluginClasspath.from(configurations.compileOnly)
}


dependencies {
    compileOnly("com.android.tools.build:gradle:3.3.0")
    compileOnly(kotlin("gradle-plugin", "1.3.50"))

    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
    testImplementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

pluginBundle {
    website = "https://github.com/kliche-dev/gradle-plugin-java8-everything"
    vcsUrl = "https://github.com/kliche-dev/gradle-plugin-java8-everything.git"

    (plugins) {
        "java8Everything" {
            displayName = "Java 8 Everything Plugin"
            tags = listOf("java8")
        }
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

//// Add a source set for the functional test suite
//val functionalTestSourceSet = sourceSets.create("functionalTest") {
//}
//
//gradlePlugin.testSourceSets(functionalTestSourceSet)
//
//// Add a task to run the functional tests
//val functionalTest by tasks.creating(Test::class) {
//    testClassesDirs = functionalTestSourceSet.output.classesDirs
//    classpath = functionalTestSourceSet.runtimeClasspath
//}
//
//val check by tasks.getting(Task::class) {
//    // Run the functional tests as part of `check`
//    dependsOn(functionalTest)
//}
