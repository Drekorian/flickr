plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
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
