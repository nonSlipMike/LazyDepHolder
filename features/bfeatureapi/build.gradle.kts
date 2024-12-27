plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
}
android {
	namespace = "com.example.b_feature_api"
}


dependencies {
	implementation(project(":common"))
	implMap(roomLibs)
}

