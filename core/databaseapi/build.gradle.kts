plugins {
	id("com.android.library")
	kotlin("android")
	id("com.google.devtools.ksp")
	id("kotlin-parcelize")
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
	namespace = "com.example.database_api"
	compileSdk = compileSdkVersionConf
	room {
		schemaDirectory("$projectDir/schemas")
	}
}

initLibDependencies()
dependencies {
	implMap(roomLibs)
	implementation(project(":features:bfeatureapi"))
}

