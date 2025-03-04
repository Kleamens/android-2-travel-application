plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    // Navigation using: https://github.com/raamcosta/compose-destinations
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"

}


android {
    namespace = "cz.mendelu.xzirchuk.project"
    compileSdk = 34

    defaultConfig {
        applicationId = "cz.mendelu.xzirchuk.project"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

//        testInstrumentationRunner = "cz.mendelu.xzirchuk.project.CustomTestRunner"
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    // Navigation
    implementation("io.github.raamcosta.compose-destinations:animations-core:1.8.42-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.54")
    //Room
//
    val room_version = "2.5.0"
//
    implementation ("androidx.room:room-ktx:$room_version")

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
//
//    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")
//
//    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

//    implementation("androidx.room:room-ktx:$room_version")
//


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("androidx.compose.material3:material3:1.1.0-alpha04")



    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")


    // Moshi for json converter
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    //GSON
    implementation ("com.google.code.gson:gson:2.9.0") // Gradle
        // implementation("com.google.code.gson:gson:2.9.0") // Gradle KTS


    //Lottie
    implementation("com.airbnb.android:lottie-compose:6.0.0")
    // DataStore
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //Maps
    implementation("com.google.maps.android:maps-compose:4.3.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("androidx.compose.foundation:foundation:1.6.0-alpha07")
    implementation("com.google.maps.android:android-maps-utils:3.5.3")
    implementation("com.google.maps.android:maps-compose-widgets:3.1.1")
    implementation("com.google.maps.android:maps-compose-utils:3.1.1")

// Layouts
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.annotation:annotation:1.2.0")
// CameraX
    implementation("androidx.camera:camera-camera2:1.0.0-SNAPSHOT")
    implementation("androidx.camera:camera-lifecycle:1.0.0-SNAPSHOT")
    implementation("androidx.camera:camera-view:1.0.0-SNAPSHOT")
    val camerax_version = "1.4.0-alpha03"


    implementation("androidx.camera:camera-core:${camerax_version}")

// For graphic overlay
    implementation("com.google.guava:guava:27.1-android")
// text Recognition
    implementation("com.google.mlkit:text-recognition:16.0.0")
    //language identification
    implementation ("com.google.mlkit:language-id:17.0.4")
    //translation
    implementation ("com.google.mlkit:translate:17.0.2")
    // For Jetpack Compose.
    implementation("com.patrykandpatrick.vico:compose:1.13.1")
    // For `compose`. Creates a `ChartStyle` based on an M3 Material Theme.
    implementation("com.patrykandpatrick.vico:compose-m3:1.13.1")
    // Houses the core logic for charts and other elements. Included in all other modules.
    implementation("com.patrykandpatrick.vico:core:1.13.1")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")


}

// For Hilt
kapt {
    correctErrorTypes = true

        javacOptions {
            // These options are normally set automatically via the Hilt Gradle plugin, but we
            // set them manually to workaround a bug in the Kotlin 1.5.20
            option("-Adagger.fastInit=ENABLED")
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }


}


