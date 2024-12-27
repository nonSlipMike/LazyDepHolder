plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}
android {
	namespace = "com.example.network_api"
}


dependencies{
	implMap(retrofitAndOkHttpLibs)
}
