plugins {
	`kotlin-dsl`
}

gradlePlugin {
	plugins.register("upload-plugin") {
		id = "upload-plugin"
		implementationClass = "com.example.myapp.UploadPlugin"
	}
}

dependencies {
	implementation("com.android.tools.build:gradle:7.4.2")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
}


