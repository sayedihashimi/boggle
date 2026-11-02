plugins {
    java
    application
}

group = "com.sedodream"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-collections:commons-collections:3.2.2")

    // SWT is only used by the small demos in com.sedodream.swtTest. The parent
    // "org.eclipse.swt" artifact uses a platform placeholder Gradle can't
    // resolve, so depend on the 64-bit Windows fragment directly.
    implementation("org.eclipse.platform:org.eclipse.swt.win32.win32.x86_64:3.130.0") {
        exclude(group = "org.eclipse.platform", module = "org.eclipse.swt")
    }

    testImplementation("junit:junit:4.13.2")
}

// Keep the original Eclipse layout: sources in src/, tests in test/.
sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(emptyList<String>())
    }
    test {
        java.setSrcDirs(listOf("test"))
        resources.setSrcDirs(emptyList<String>())
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

application {
    mainClass = "hw4.boggle.ParallelBoggleEvaluator"
}

// The app and tests read boards and dictionaries from ./files and write
// results to ./output, both relative to the project root.
val outputDir = layout.projectDirectory.dir("output").asFile

tasks.named<JavaExec>("run") {
    workingDir = rootDir
    maxHeapSize = "2g"
    doFirst { outputDir.mkdirs() }
}

// The slow large-board and perf tests are marked @Ignore in the source,
// so the default test run takes only a few seconds.
tasks.test {
    useJUnit()
    workingDir = rootDir
    maxHeapSize = "2g"
    doFirst { outputDir.mkdirs() }
    testLogging {
        events("failed", "skipped")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
    }
}
