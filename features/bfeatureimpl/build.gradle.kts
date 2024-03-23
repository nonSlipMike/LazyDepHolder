plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
android { compileSdk = compileSdkVersionConf }

initLibDependencies()
dependencies {
    //implementation(project(":core:modulinjection"))
    implementation(project(":common"))
    implementation(project(":features:bfeatureapi"))

}

