plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "cz.drekorian.android.flickr.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlinOptions {
        moduleName = "cz.drekorian.android.flickr.shared"
    }
}

dependencies {
    implementation(libs.koin)
    implementation(libs.androidx.lifecycle.process)
}
