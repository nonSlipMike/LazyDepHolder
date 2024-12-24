plugins {
	id("com.android.library")
	id("org.jetbrains.kotlin.android")
	id("com.google.devtools.ksp")
	id("androidx.navigation.safeargs.kotlin")
	id("kotlin-parcelize")
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
	namespace = "com.example.a_feature_impl"
	compileSdk = compileSdkVersionConf
	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
}

initLibDependencies()

dependencies {
	implementation(project(":features:afeatureapi"))
	implementation(project(":common"))
	implementation(project(":core:networkapi"))
	implementation(project(":features:cfeatureapi"))
}