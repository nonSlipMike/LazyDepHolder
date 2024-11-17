plugins {
    id("com.android.library")
    kotlin("android")
    //kotlin("kapt")
    id ("com.google.devtools.ksp")
}
android { compileSdk = compileSdkVersionConf }

initLibDependencies()
dependencies {
    implementation(project(":common"))
}