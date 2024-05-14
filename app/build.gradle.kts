plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "tj.tajsoft.loyalrsn"
    compileSdk = 34

    defaultConfig {
        applicationId = "tj.tajsoft.loyalrsn"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Activity KTX for viewmodel()
    implementation("androidx.activity:activity-ktx:1.8.1")

    //Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //daggerHilt

    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    //Coroutines Lifecycle Scope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


    // Kotlin
    val nav_version = "2.7.3"
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

//    glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    //picasso
    implementation ("com.squareup.picasso:picasso:2.8")

    implementation ("com.google.android.gms:play-services-maps:17.0.0")

    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-messaging:23.4.1")
    implementation ("com.google.maps.android:android-maps-utils:2.2.0")



    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")

    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.2")

    implementation( "io.github.chaosleung:pinview:1.4.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    //datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //swipe refreshing
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")


}