@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.nugu.ya.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }
    
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // okhttp
    implementation(libs.okhttp.urlconnection)
    implementation(libs.okhttp.logging.interceptor)
}