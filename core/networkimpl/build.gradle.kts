plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
android { compileSdk = compileSdkVersionConf }

initLibDependencies()
dependencies {
    implementation(project(":core:networkapi"))
    implementation(project(":common"))
}

