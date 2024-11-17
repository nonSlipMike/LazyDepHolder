plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id ("kotlin-parcelize")
}
android {
    compileSdk = compileSdkVersionConf
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
}
initLibDependencies()

dependencies {
    implementation(project(":features:afeatureapi"))
    implementation(project(":common"))
    implementation(project(":core:networkapi"))
    implementation(project(":features:cfeatureapi"))
}