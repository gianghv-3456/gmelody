
object Versions {

    const val gradlePlugin = "7.4.2"
    const val kotlin = "1.7.20"

    const val coreKtx = "1.9.0"
    const val appCompat = "1.6.1"
    const val material = "1.8.0"
    const val constraintLayout = "2.1.4"
    const val coreSplashScreen = "1.0.0"

    const val lifecycle = "2.5"
    const val navigation = "2.5.3"
    const val glide = "4.11.0"
    const val koin = "3.4.0"

    const val jacoco = "0.8.9"
    const val jUnit = "4.13.2"
    const val mockk = "1.13.2"

    const val ktlint = "0.47.0"
    const val detekt = "1.22.0"

    const val gson = "2.10.1"
}

object AppConfigs {
    const val application_id = "com.sun.gmelody"
    const val compile_sdk_version = 33
    const val min_sdk_version = 23
    const val target_sdk_version = 33 // improve later
    const val version_code = 1
    const val version_name = "1.0"
    const val version_code_release = 1
    const val version_name_release = "1.0"
}

object ClassPath {
    const val gradle_build_tools = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Plugins {
    const val android_application = "com.android.application"
    const val kotlin_android = "android"
    const val kotlinApt = "kapt"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val kotlinParcelize = "kotlin-parcelize"
}

object Deps {
    const val core_ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Splash screen
    const val splash_screen = "androidx.core:core-splashscreen:${Versions.coreSplashScreen}"

    // lifecycle
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // navigation
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Glide
    const val glide_runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // koin
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin}"
    const val koin_test = "io.insert-koin:koin-test:${Versions.koin}"

    // Testing
    const val junit = "junit:junit:${Versions.jUnit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    // Ktlint
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

}
