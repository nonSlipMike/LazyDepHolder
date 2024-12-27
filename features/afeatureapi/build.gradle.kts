plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}
android {
	namespace = "com.example.a_feature_api"
}


dependencies {
	implementation(project(":common"))
}