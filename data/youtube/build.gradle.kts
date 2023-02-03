@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.nugu.ya.data.youtube"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "API_KEY", "\"AIzaSyBMNC-f5BZJEvMfKW1Ixf2OGC56Cv5qDTw\"")
    }
}

dependencies {
    implementation(project(":core"))

    // coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)

    // okhttp
    implementation(libs.okhttp.urlconnection)
    implementation(libs.okhttp.logging.interceptor)

    // paging
    implementation(libs.androidx.paging.runtime)
}