plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
	id("org.jetbrains.kotlin.plugin.compose")
}
android {
	namespace = "com.example.c_feature_impl"
}


dependencies {
	implMap(roomLibs)
	implementation(project(":common"))
	implementation(project(":core:databaseapi"))
	implementation(project(":features:bfeatureapi"))
	implementation(project(":features:cfeatureapi"))
}