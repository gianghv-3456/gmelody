// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    apply(from = "$rootDir/team-props/git-hooks.gradle.kts")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (ClassPath.gradle_build_tools)
        classpath (ClassPath.kotlin_gradle_plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
