plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.example.vehicontrol"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vehicontrol"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material:1.2.0")




    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //icons
    implementation ("androidx.compose.material:material-icons-extended")


    implementation ("androidx.camera:camera-core:1.1.0")
    implementation ("androidx.camera:camera-camera2:1.1.0")

//Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//Compose Navigation
    val nav_version = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.45")
    kapt ("com.google.dagger:hilt-compiler:2.45")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //// CAMERA STUFF ////
    implementation ("androidx.camera:camera-camera2:1.3.0-beta02")
    implementation ("androidx.camera:camera-lifecycle:1.3.0-beta02")
    implementation ("androidx.camera:camera-view:1.3.0-beta02")
    implementation ("androidx.camera:camera-extensions:1.3.0-beta02")

    //Permission
    implementation ("com.google.accompanist:accompanist-permissions:0.31.6-rc")

    //room
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.2.0")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.0")

    implementation("com.squareup.okhttp3:okhttp:4.9.0") // Assurez-vous d'utiliser la bonne version
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.0") // Pour la gestion des connexions
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") // Facultatif pour le logging


}