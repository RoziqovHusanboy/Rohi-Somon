// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("UNUSED_EXPRESSION")
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
        val nav_version = "2.7.4"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath( "com.google.dagger:hilt-android-gradle-plugin:2.48")
        val kspVersion by extra("1.6.10-1.0.2")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kspVersion")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false



}