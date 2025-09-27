plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
    compilerOptions {
        moduleName = "cz.drekorian.android.flickr.shared"
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = kotlin.compilerOptions.moduleName.get()
}

dependencies {
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.koin)
}
