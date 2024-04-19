plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "cz.drekorian.android.flickr.flickr.impl"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlinOptions {
        jvmTarget = "1.8"
        moduleName = "cz.drekorian.android.flickr.flickr.impl"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
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
