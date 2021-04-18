plugins {
    `java-library`
    id("com.github.sherter.google-java-format") version "0.9"
    `maven-publish`
    signing
}

group = project.property("GROUP_ID")!!
version = project.property("MODULE_NAME")!!

repositories {
    jcenter()
}

dependencies {
    implementation("io.github.vincenzopalazzo:material-ui-swing:1.1.2-rc2")
    testImplementation("io.github.material-ui-swing:SwingSnackBar:0.0.2")
    testImplementation("com.github.jiconfont:jiconfont-google_material_design_icons:2.2.0.2")
}
/*
plugins.withType<JavaPlugin>().configureEach {
    configure<JavaPluginExtension> {
        modularity.inferModulePath.set(true)
    }
}

java {
    modularity.inferModulePath.set(true)
}
*/
//TODO I'm using this because I will create a Multi-Release JAR Files
//https://openjdk.java.net/jeps/238
tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to project.property("MODULE_NAME").toString())
    }
}
tasks{
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        from(sourceSets["main"].allSource)
    }

    withType<JavaCompile>().configureEach {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "ISO-8859-1"
    }

    withType<Jar>().configureEach {
        // add META-INF/LICENSE to all created JARs
        from("${rootDir}/LICENSE") {
            into("META-INF")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    credentials {
                        username = project.property("sonatypeUsername").toString()
                        password = project.property("sonatypePassword").toString()
                    }
                    val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                    val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
                    url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                }
            }

            pom {
                name.set(project.name)
                description.set("Simple Swing component with placeholder called JTextFieldPlaceholder")
                url.set("https://github.com/material-ui-swing/JTextFieldPlaceholder")

                licenses {
                    license {
                        name.set("BSD 3-Clause \"New\" or \"Revised\"")
                        url.set("https://github.com/material-ui-swing/SwingSnackBar/blob/master/LICENSE.md")
                    }
                }

                developers {
                    developer {
                        id.set("vincenzopalazzo")
                        name.set("Vincenzo Palazzo")
                        email.set("vincenzopalazzodev@gmail.com")
                        url.set("https://github.com/vincenzopalazzo")
                        roles.addAll("developer")
                        timezone.set("Europe/Rome")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/material-ui-swing/JTextFieldPlaceholder.git")
                    developerConnection.set("scm:git:ssh://github.com:material-ui-swing/JTextFieldPlaceholder.git")
                    url.set("https://github.com/material-ui-swing/JTextFieldPlaceholder.git")
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(tasks["sourcesJar"], tasks["javadocJar"])
    sign(publishing.publications[project.name])
}