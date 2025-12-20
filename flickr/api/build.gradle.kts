plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
}

android {
    namespace = "cz.drekorian.android.flickr.flickr.api"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlin {
        compilerOptions {
            moduleName = "cz.drekorian.android.flickr.flickr.api"
        }
    }
}

dependencies {
    api(libs.kotlinx.serialization.json)

    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.android)
}
