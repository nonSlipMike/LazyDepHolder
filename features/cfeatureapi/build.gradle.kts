plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}
android {
	namespace = "com.example.c_feature_api"
}


dependencies {
	implementation(project(":common"))
}

