plugins {
	id("com.android.library")
	kotlin("android")
	id("com.google.devtools.ksp")
}
android {
	compileSdk = compileSdkVersionConf
	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
}

initLibDependencies()
dependencies {
	implementation(project(":common"))
	implementation(project(":features:bfeatureapi"))
	implementation(project(":features:afeatureapi"))
	implementation(project(":features:cfeatureapi"))

}

