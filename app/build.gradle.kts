plugins {
	id("com.google.devtools.ksp")
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("androidx.navigation.safeargs.kotlin")
	id("upload-plugin")
	id("org.jetbrains.kotlin.plugin.compose")
}


androidComponents {
	beforeVariants { variantBuilder ->
		// Отключаем ненужные варианты
		if (variantBuilder.buildType == "debug" && variantBuilder.flavorName == "prod") {
			variantBuilder.enable = false
		}
		if (variantBuilder.buildType == "release" && variantBuilder.flavorName == "dev") {
			variantBuilder.enable = false
		}
	}
}

android {
	namespace = "com.example.myapp"
	defaultConfig {
		applicationId = appIdConf
		minSdk = minSdkVersionConf
		targetSdk = compileSdkVersionConf
		versionCode = appVersionCodeConf
		versionName = appVersionNameConf
		testInstrumentationRunner = testInstrumentRunnerConf
	}

	flavorDimensions.add("main")

	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
		debug {
			isMinifyEnabled = false
			isShrinkResources = false
		}
	}

	productFlavors {
		create("prod") {
			buildConfigField("String", "BASE_SERVER", "\"https://mycorp.com\"")
		}
		create("dev") {
			applicationIdSuffix = applicationIdSuffixConf // ".dev"
			versionNameSuffix = versionNameSuffixConf // "-dev"
			buildConfigField("String", "BASE_SERVER", "\"https://dev.mycorp.com\"")
		}
	}
	sourceSets {
		getByName("main") {
			assets {
				srcDirs("src/main/assets")
			}
		}
	}
}

dependencies {
	implMap(roomLibs)
	implementation(project(":common"))
	//core
	implementation(project(":core:networkimpl"))
	implementation(project(":core:networkapi"))
	implementation(project(":core:databaseimpl"))
	implementation(project(":core:databaseapi"))
	//feature
	implementation(project(":features:afeatureimpl"))
	implementation(project(":features:bfeatureimpl"))
	implementation(project(":features:cfeatureimpl"))
	implementation(project(":features:afeatureapi"))
	implementation(project(":features:bfeatureapi"))
	implementation(project(":features:cfeatureapi"))
}
// подключили файл гредла со скриптом
//apply(from = "unusedClasses.gradle.kts")
