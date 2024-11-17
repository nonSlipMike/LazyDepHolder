plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id ("com.google.devtools.ksp")
	id("androidx.navigation.safeargs.kotlin")
	id("upload-plugin")
}

android {
	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}//need to use viewModel
	namespace = "com.example.myapp"
	compileSdk = compileSdkVersionConf

	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer

	defaultConfig {
		applicationId = appIdConf
		minSdk = minSdkVersionConf
		targetSdk = compileSdkVersionConf
		versionCode = appVersionCodeConf
		versionName = appVersionNameConf
		testInstrumentationRunner = testInstrumentRunnerConf
	}

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
	flavorDimensions.add("main")
	productFlavors {
		create("prod") {
			buildConfigField("String", "BASE_SERVER", "\"https://mycorp.com\"")

		}
		create("dev") {
			applicationIdSuffix = applicationIdSuffixConf//".dev"
			versionNameSuffix = versionNameSuffixConf//"-dev"
			buildConfigField("String", "BASE_SERVER", "\"https://dev.mycorp.com\"")
		}
	}
	variantFilter {
		this.ignore = name == "prodDebug" || name == "devRelease"
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	sourceSets {
		getByName("main") {
			assets {
				srcDirs("src/main/assets")
			}
		}
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

extra["androidSdkDirectory"] = android.sdkDirectory.absolutePath
extra["compileSdkVersion"] = compileSdkVersionConf.toString()
initLibDependencies()
dependencies {

	//core
	implementation(project(":core:database"))
	implementation(project(":common"))
	implementation(project(":core:networkimpl"))
	implementation(project(":core:networkapi"))
	implementation(project(":features:afeatureimpl"))
	implementation(project(":features:bfeatureimpl"))
	implementation(project(":features:cfeatureimpl"))
	implementation(project(":features:afeatureapi"))
	implementation(project(":features:bfeatureapi"))
	implementation(project(":features:cfeatureapi"))
	//feature
}

apply(from = "unusedClasses.gradle.kts")
