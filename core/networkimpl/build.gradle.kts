plugins {
	id("com.android.library")
	kotlin("android")
	id("com.google.devtools.ksp")
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
	namespace = "com.example.network_impl"
	compileSdk = compileSdkVersionConf
}

initLibDependencies()

dependencies {
	implementation(project(":core:networkapi"))
	implementation(project(":common"))
}

