plugins {
	id("com.google.devtools.ksp")
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("kotlin-parcelize")
}
ksp {
	arg("room.incremental", "true") // Опционально
	arg("room.generateKotlin", "true") // Опционально
}
android {
	namespace = "com.example.database_impl"
}
dependencies {
	implMap(roomLibs)
	implementation(project(":common"))
	implementation(project(":core:databaseapi"))
	implementation(project(":features:bfeatureapi"))
}

