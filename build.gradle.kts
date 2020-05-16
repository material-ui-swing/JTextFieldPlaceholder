plugins {
    `java-library`
    `maven-publish`
}

group = project.property("GROUP_ID")!!
version = project.property("VERSION")!!

repositories {
    jcenter()
}

dependencies {
    // Use JUnit test framework
    implementation(files("$projectDir/devlib/material-ui-swing-1.1.1-rc2.jar"))
    implementation(files("$projectDir/devlib/SwingSnackBar-0.0.1.jar"))
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to project.property("MODULE_NAME"))
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/vincenzopalazzo/JTextFieldPlaceholder")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr", MavenPublication::class) {
            from(components["java"])
        }
    }
}
