plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.kapt")
	id("androidx.navigation.safeargs.kotlin")
}

android {
	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}//need to use viewModel
	namespace = "com.example.myapp"
	compileSdk = compileSdkVersionConf
	buildFeatures {
		viewBinding = true
	}

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
initLibDependencies()
dependencies {

	//core
	implementation(project(":core:database"))
	implementation(project(":common"))
	implementation(project(":core:networkimpl"))
	implementation(project(":core:networkapi"))
	implementation(project(":core:composeroot"))
	implementation(project(":features:afeatureimpl"))
	implementation(project(":features:bfeatureimpl"))
	implementation(project(":features:cfeatureimpl"))
	implementation(project(":features:afeatureapi"))
	implementation(project(":features:cfeatureapi"))
	implementation("com.google.android.material:material:1.4.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
	implementation("androidx.navigation:navigation-ui-ktx:2.5.3")


	//feature
}