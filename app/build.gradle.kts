plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

group = "me.abc"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "me.abc.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha02"
    }
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")


    //COmpose
    implementation ("androidx.compose.ui:ui:1.2.0-alpha02")
    implementation ("androidx.compose.material:material:1.2.0-alpha02")
    implementation( "androidx.compose.foundation:foundation:1.2.0-alpha02")

//    implementation( "androidx.compose.compiler:compiler:1.0.0-beta04")
    implementation( "androidx.activity:activity-compose:1.5.0-alpha01")
    implementation( "androidx.navigation:navigation-compose:2.5.0-alpha01")

    implementation ("androidx.compose.ui:ui-tooling:1.2.0-alpha02")
    implementation ("androidx.compose.runtime:runtime:1.2.0-alpha02")
    implementation ("androidx.compose.compiler:compiler:1.2.0-alpha02")

//Icon
// Material design icons
    implementation ("androidx.compose.material:material-icons-core:1.2.0-alpha02")
    implementation ("androidx.compose.material:material-icons-extended:1.2.0-alpha02")


    // Needed for viewmodel to do constructor injection
    implementation("androidx.hilt:hilt-navigation:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    implementation("com.google.dagger:hilt-android:2.40.5")


//logging interceptor
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

//glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
}
