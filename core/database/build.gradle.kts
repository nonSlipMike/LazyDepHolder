plugins {
    id("com.android.library")
    kotlin("android")
    id ("com.google.devtools.ksp")

}
android { compileSdk = compileSdkVersionConf }

initLibDependencies()

