plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "cz.drekorian.android.flickr.flickr.api"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlinOptions {
        jvmTarget = "1.8"
        moduleName = "cz.drekorian.android.flickr.flickr.api"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(libs.kotlinx.serialization.json)

    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.android)
}
