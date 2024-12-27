plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("androidx.navigation.safeargs.kotlin")
	id("kotlin-parcelize")
	id("org.jetbrains.kotlin.plugin.compose")
}
android {
	namespace = "com.example.a_feature_impl"
}


dependencies {
	implementation(project(":features:afeatureapi"))
	implementation(project(":common"))
	implementation(project(":core:networkapi"))
	implementation(project(":features:cfeatureapi"))
}