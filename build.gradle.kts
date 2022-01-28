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
    "graderImplementation"("org.sourcegrade:jagr-launcher:0.4.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

application {
    mainClass.set("h12.Main")
}

tasks {
    test {
        useJUnitPlatform()
    }
    val graderTest by creating(Test::class) {
        group = "verification"
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
        }
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    jar {
        enabled = false
    }
}
