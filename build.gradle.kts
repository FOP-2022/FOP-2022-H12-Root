plugins {
    java
    application
    id("org.sourcegrade.style") version "1.2.0"
}

tasks {
    create<Jar>("graderJar") {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H13-${project.version}.jar")
            from(sourceSets.main.get().allSource)
            from(sourceSets.test.get().allSource)
        }
    }
}

application {
    mainClass.set("h12.Main")
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.sourcegrade.style")
    version = "0.1.0-SNAPSHOT"
    repositories {
        mavenCentral()
    }
    java {
        withSourcesJar()
    }
    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
        jar {
            enabled = false
        }
    }
}
