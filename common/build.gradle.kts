plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
android {
    compileSdk = compileSdkVersionConf
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
}

initLibDependencies()