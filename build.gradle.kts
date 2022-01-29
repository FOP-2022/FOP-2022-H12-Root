plugins {
    java
    application
    id("org.sourcegrade.style") version "1.2.0"
}

version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

val grader: SourceSet by sourceSets.creating {
    compileClasspath += sourceSets.test.get().compileClasspath
    runtimeClasspath += output + compileClasspath
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    "graderImplementation"("org.sourcegrade:jagr-launcher:0.4.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

application {
    mainClass.set("h12.Main")
}

tasks {
    val runDir = File("build/run")
    named<JavaExec>("run") {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
    }
    test {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
        useJUnitPlatform()
    }
    val graderTest by creating(Test::class) {
        group = "verification"
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
        testClassesDirs = grader.output.classesDirs
        classpath = grader.runtimeClasspath
        useJUnitPlatform()
    }
    named("check") {
        dependsOn(graderTest)
    }
    create<Jar>("graderJar") {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H12-${project.version}.jar")
            from(sourceSets.main.get().allSource)
            from(sourceSets.test.get().allSource)
            from(grader.allSource)
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    jar {
        enabled = false
    }
}
