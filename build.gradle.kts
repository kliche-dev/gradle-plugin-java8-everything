plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "0.10.1"

}

version = "1.0"
description = "Plugin for automatically setting source/targetCompatibility of all modules in project to Java 8"

repositories {
    google()
    mavenCentral()
    jcenter()
}

configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("java8Everything") {
            id = "kliche.java8-everything"
            displayName = "Java 8 Everything Plugin"
            description = project.description
            implementationClass = "dev.kliche.plugin.java8everything.Java8EverythingPlugin"
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
    val androidPluginVersion = "3.5.1"
    compileOnly("com.android.tools.build:gradle:$androidPluginVersion")
    compileOnly(kotlin("gradle-plugin", "1.3.70"))

    testImplementation("com.android.tools.build:gradle:$androidPluginVersion")
    testImplementation(gradleTestKit())
    testImplementation("junit:junit:4.12")

    // Use the kotest test library.
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.0-BETA2") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.0-BETA2") // for kotest core jvm assertions


    testImplementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
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
