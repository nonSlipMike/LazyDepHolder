plugins {
	`kotlin-dsl`
}

gradlePlugin {
	plugins {
		register("upload-plugin") {
			id = "upload-plugin"  // ID вашего плагина
			implementationClass = "com.example.myapp.UploadPlugin" // Класс реализации плагина
		}
	}
}

dependencies {
	implementation("com.android.tools.build:gradle:$gradleConfVer")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
}


