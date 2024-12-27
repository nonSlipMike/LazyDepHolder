plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
}
android {
	namespace = "com.example.network_impl"
}

dependencies {
	implementation(project(":core:networkapi"))
	implementation(project(":common"))
}

