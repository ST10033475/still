plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.still"
    compileSdk = 34

    buildFeatures {
        buildConfig = true // Enable BuildConfig
    }

    defaultConfig {
        applicationId = "com.example.still"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"AIzaSyDbAtrWoL5o2B72BGvNZRnBWi4DnFO9PFw\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //gifs
    implementation ("com.github.bumptech.glide:glide:4.15.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.0")


    //Biometrics

    implementation ("androidx.biometric:biometric:1.1.0")


    //button layout

    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.android.material:material:1.8.0") // Check for the latest version

    //Gemini
    //dependency for the Google AI client SDK for Android
    implementation("com.google.ai.client.generativeai:generativeai:0.1.2")

    //songs

    implementation (
        "com.squareup.retrofit2:retrofit:2.9.0"
    )
    implementation (
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1"
    )
    implementation (
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"
    )
    implementation (
        "com.squareup.retrofit2:converter-gson:2.9.0"
    )
    implementation (
        "com.google.android.exoplayer:exoplayer:2.15.1"
    )
    implementation(
        "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    )

    /// Google sign in
    implementation("androidx.credentials:credentials:1.3.0-alpha01")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    // google authentication
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    implementation("com.google.firebase:firebase-bom:33.3.0")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("androidx.credentials:credentials:1.2.2")




}