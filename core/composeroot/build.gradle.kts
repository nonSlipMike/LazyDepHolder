plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.kapt")
	id("androidx.navigation.safeargs.kotlin")
}
android {
	compileSdk = compileSdkVersionConf
	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
}

initLibDependencies()
dependencies {
	implementation(project(":common"))
	implementation(project(":features:cfeatureimpl"))
	implementation(project(":features:cfeatureapi"))
	implementation(project(":features:afeatureapi"))


}