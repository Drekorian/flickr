plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "cz.drekorian.android.flickr.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlinOptions {
        jvmTarget = "1.8"
        moduleName = "cz.drekorian.android.flickr.shared"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.koin)
    implementation(libs.androidx.lifecycle.process)
}
