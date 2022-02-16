plugins {
    id(Plugins.android_application)
    kotlin(Plugins.kotlin_android)
    id(Plugins.detekt).version(Versions.detekt)
    jacoco
}

buildscript {
    apply(from = "ktlint.gradle.kts")
    apply(from = "autodimension.gradle.kts")
}

android {
    compileSdk = AppConfigs.compile_sdk_version

    defaultConfig {
        applicationId = AppConfigs.application_id
        minSdk = AppConfigs.min_sdk_version
        targetSdk = AppConfigs.target_sdk_version
        versionCode = AppConfigs.version_code
        versionName = AppConfigs.version_name
    }

    flavorDimensions("appVariant")
    productFlavors {
        create("dev") {
            setDimension("appVariant")
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Structure-Dev")
        }
        create("prd") {
            setDimension("appVariant")
            resValue("string", "app_name", "Structure")
            versionCode = AppConfigs.version_code_release
            versionName = AppConfigs.version_name_release
        }
    }

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml") // config rules file
    input = files("src/main/java")
    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
        txt.enabled = false // similar to the console output, contains issue signature to manually edit baseline files
    }
}

jacoco {
    toolVersion = Versions.jacoco
}

project.afterEvaluate {
    // Grab all build types and product flavors
    val buildTypeNames: List<String> = android.buildTypes.map { it.name }
    val productFlavorNames: ArrayList<String> = ArrayList(android.productFlavors.map { it.name })
    // When no product flavors defined, use empty
    if (productFlavorNames.isEmpty()) productFlavorNames.add("")
    productFlavorNames.forEach { productFlavorName ->
        buildTypeNames.forEach { buildTypeName ->
            val sourceName: String
            val sourcePath: String

            if (productFlavorName.isEmpty()) {
                sourcePath = buildTypeName
                sourceName = buildTypeName
            } else {
                sourcePath = "$productFlavorName/$buildTypeName"
                sourceName = "$productFlavorName${buildTypeName.capitalize()}"
            }
            val testTaskName = "test${sourceName.capitalize()}UnitTest"
            // Create coverage task of form 'testFlavorTypeCoverage' depending on 'testFlavorTypeUnitTest'
            // Ex: ./gradlew testDebugUnitTestCoverage
            task<JacocoReport>("${testTaskName}Coverage") {
                // where store all test to run follow second way above
                group = "coverage"
                description = "Generate Jacoco coverage reports on the ${sourceName.capitalize()} build."
                val excludeFiles = setOf(
                    "androidx/**/*.class",
                    "**/BR.class",
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/Manifest*.*",
                    "**/*Test*.*",
                    "android/**/*.*",
                    "**/*Application*.*",
                    "**/*Activity*.*",
                    "**/*Fragment*.*",
                    "**/*Adapter*.*",
                    "**/*Dialog*.*",
                    "**/*Args*.*",
                    "**/*Companion*.*",
                    "**/Lambda*.*",
                    "**/*Lambda*.*",
                    "**/*Binder*.*",
                )

                //Explain to Jacoco where are you .class file java and kotlin
                classDirectories.setFrom(
                    fileTree("build/intermediates/classes/$sourcePath").exclude(excludeFiles),
                    fileTree("build/tmp/kotlin-classes/$sourceName").exclude(excludeFiles)
                )
                val coverageSourceDirs = arrayListOf(
                    "src/main/java",
                    "src/$productFlavorName/java",
                    "src/$buildTypeName/java"
                )

                additionalSourceDirs.setFrom(files(coverageSourceDirs))

                //Explain to Jacoco where is your source code
                sourceDirectories.setFrom(files(coverageSourceDirs))

                //execute file .exec to generate data report
                executionData.setFrom(
                    fileTree(
                        mapOf(
                            "dir" to layout.buildDirectory,
                            "includes" to listOf("**/*.exec")
                        )
                    )
                )

                reports {
                    xml.isEnabled = false
                    html.isEnabled = true
                    html.outputLocation.set(layout.buildDirectory.dir("reports/tests/coverage/${testTaskName}Report"))
                }
                dependsOn(testTaskName)
            }
        }
    }
}

tasks {
    check {
        dependsOn("ktlintCheck")
        dependsOn("ktlintFormat")
    }
    clean {
        dependsOn("createDimen")
        mustRunAfter("createDimen")
    }
}

dependencies {
    implementation(Deps.core_ktx)
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraint_layout)

    testImplementation(Deps.junit)
    testImplementation(Deps.mockk)
}
