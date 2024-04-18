plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "cz.drekorian.android.flickr.flickr.api"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlinOptions {
        moduleName = "cz.drekorian.android.flickr.flickr.api"
    }
}

dependencies {
    api(libs.kotlinx.serialization.json)

    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.android)
}
