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
dependencies {
	//implementation(project(":core:modulinjection"))
	implementation(project(":common"))
	implementation(project(":features:cfeatureapi"))
}