plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "iesch.org.practica06"
    compileSdk = 36

    defaultConfig {
        applicationId = "iesch.org.practica06"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding=true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Firebase BOM estable
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))

// Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation ("com.google.firebase:firebase-auth-ktx")

    implementation (platform("com.google.firebase:firebase-bom:33.5.1"))

    implementation ("com.google.firebase:firebase-messaging-ktx")
    implementation ("com.google.firebase:firebase-config-ktx")
// Firebase Auth
    implementation("com.google.firebase:firebase-auth")

// Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

// Google Auth
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

// Cloud Messaging
    implementation("com.google.firebase:firebase-messaging")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Picasso
    implementation("com.squareup.picasso:picasso:2.8")
    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    //Mapbox
    implementation("com.mapbox.maps:android:11.4.0")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}