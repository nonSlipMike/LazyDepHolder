plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
}
android {
	namespace = "com.example.database_api"
}
dependencies {
	implMap(roomLibs)
}


