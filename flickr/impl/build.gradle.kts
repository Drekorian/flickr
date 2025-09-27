plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
}

kotlin {
    compilerOptions {
        moduleName = "cz.drekorian.android.flickr.flickr.impl"
        freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = kotlin.compilerOptions.moduleName.get()
}

dependencies {
    api(project(":flickr:api"))
    implementation(project(":shared"))

    implementation(libs.koin)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
}
