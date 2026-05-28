import javax.naming.Binding

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "papo.hun.papomanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "papo.hun.papomanager"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation ("androidx.camera:camera-core:1.3.0-alpha04")
    implementation ("androidx.camera:camera-camera2:1.3.0-alpha04")
    implementation ("androidx.camera:camera-lifecycle:1.3.0-alpha04")
    implementation ("androidx.camera:camera-video:1.3.0-alpha04")
    implementation ("androidx.camera:camera-view:1.3.0-alpha04")
    implementation ("androidx.camera:camera-mlkit-vision:1.3.0-alpha04")
    implementation ("androidx.camera:camera-extensions:1.3.0-alpha04")

    implementation ("com.google.android.gms:play-services-location:20.0.0")
    implementation(platform("com.google.firebase:firebase-bom:33.4.0"))
    // 버전을 제거하여 BoM 버전을 따르도록 수정
    implementation ("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")

    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.gridlayout)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
