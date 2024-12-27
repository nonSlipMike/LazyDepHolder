plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
}

val gradleConfVer = "8.3.0" // Версия Android Gradle Plugin
val kotlinVer = "2.0.21"    // Версия Kotlin

gradlePlugin {
	plugins {
		register("upload-plugin") {
			id = "upload-plugin"
			implementationClass = "com.example.myapp.UploadPlugin"
		}
	}
}


dependencies {
	implementation("com.android.tools.build:gradle:$gradleConfVer")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
}




