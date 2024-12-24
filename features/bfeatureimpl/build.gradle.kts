plugins {
	id("com.android.library")
	kotlin("android")
	id("com.google.devtools.ksp")
	id("kotlin-parcelize")
	id("androidx.room")
	id("org.jetbrains.compose")

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
	namespace = "com.example.b_feature_impl"
	compileSdk = compileSdkVersionConf
	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
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
	implementation(project(":features:afeatureapi"))
	implementation(project(":features:cfeatureapi"))

}

