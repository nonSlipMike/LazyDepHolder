plugins {
	id("com.android.library")
	kotlin("android")
	id("kotlin-parcelize")
	id("com.google.devtools.ksp")
	id("androidx.room")

}
java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = "17" // Указываем JVM-таргет для Kotlin
	}
}
android {
	namespace = "com.example.database_impl"
	compileSdk = compileSdkVersionConf
	room {
		schemaDirectory("$projectDir/schemas")
	}
}

initLibDependencies()

dependencies {
	implMap(roomLibs)
	implementation(project(":common"))
	implementation(project(":core:databaseapi"))
	implementation(project(":features:bfeatureapi"))
}

