plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.72"

    id("com.gradle.plugin-publish") version "0.12.0"
}

version = "1.1"
description = "Plugin for automatically setting source/targetCompatibility of all modules in project"

repositories {
    google()
    mavenCentral()
    jcenter()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("javaVersionEverything") {
            id = "kliche.java-version-everything"
            displayName = "Java Version Everything Plugin"
            description = "Plugin for automatically setting source/targetCompatibility of all modules in project"
            implementationClass = "dev.kliche.plugin.java_version_everything.JavaVersionEverythingPlugin"
        }

        create("javaVersionEverythingAndroid") {
            id = "kliche.java-version-everything-android"
            displayName = "Java Version Everything Plugin With Android Support"
            description = "Plugin for automatically setting source/targetCompatibility of all modules in project with android support"
            implementationClass = "dev.kliche.plugin.java_version_everything.AndroidJavaVersionEverythingPlugin"
        }

        create("java8Everything") {
            id = "kliche.java8-everything"
            displayName = "Java 8 Everything Plugin"
            description = "Convenience plugin for people using the previous non generic version of the Java Version Everything plugin"
            implementationClass = "dev.kliche.plugin.java_version_everything.Java8EverythingPlugin"
        }
    }
}

// Ignore warning about using experimental features
//kotlinDslPluginOptions {
//    experimentalWarning.set(false)
//}

// THIS! This is what solved the test problems.
tasks.withType<PluginUnderTestMetadata>().configureEach {
    pluginClasspath.from(configurations.compileOnly)
}


dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    val androidPluginVersion = "4.0.0"
//    val androidPluginVersion = "3.5.1"
    compileOnly("com.android.tools.build:gradle:$androidPluginVersion")
    compileOnly(kotlin("gradle-plugin"/*, "1.3.70"*/))


    testImplementation("com.android.tools.build:gradle:$androidPluginVersion")
    testImplementation(gradleTestKit())

    testImplementation(kotlin("gradle-plugin"))
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

pluginBundle {
    website = "https://github.com/kliche-dev/gradle-plugin-java8-everything"
    vcsUrl = "https://github.com/kliche-dev/gradle-plugin-java8-everything.git"

    (plugins) {
        "javaVersionEverything" {
            displayName = "Java Version Everything Plugin"
            tags = listOf("java", "kotlin")
        }
        "javaVersionEverythingAndroid" {
            displayName = "Java Version Everything Plugin (With Android Support)"
            tags = listOf("java", "kotlin", "android")
        }
        "java8Everything" {
            displayName = "Java 8 Everything Plugin"
            tags = listOf("java8")
        }
    }
}

tasks {
    test {
        useJUnitPlatform { }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
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
