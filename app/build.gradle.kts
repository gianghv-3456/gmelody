plugins {
    id(Plugin.android_application)
    kotlin(Plugin.kotlin_android)
}

buildscript {
    apply(from = "ktlint.gradle.kts")
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



dependencies {
    implementation(Deps.core_ktx)
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation(Deps.constraint_layout)

    testImplementation(Deps.junit)
    testImplementation(Deps.mockk)
}
