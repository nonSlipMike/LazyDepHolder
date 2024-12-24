/**
 *  plugins {
 *      `kotlin-dsl`
 *  }
 *
 *  repositories {
 *      jcenter()
 *  }
 *
 * this class used as box for gradle.kts files
 * this class need to be in buildSrc/src/main/kotlin/Config.kt
 * this module need to have raw in this build.gradle.kts
 *
 * more func may to find:
 * https://medium.com/swlh/efficiently-create-multi-module-android-project-with-custom-plugin-7632a8b6f325
 */
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.file.ConfigurableFileTree

//system
const val gradleConfVer = "8.2.0"
const val minSdkVersionConf = 21
const val compileSdkVersionConf = 34
const val appVersionCodeConf = 1
const val appVersionNameConf = "1.0.0"
const val appIdConf = "com.example.new.dagger_arch"
const val testInstrumentRunnerConf = "androidx.test.runner.AndroidJUnitRunner"
const val applicationIdSuffixConf = ".dev"
const val versionNameSuffixConf = "-dev"

//compose
const val composeMaterial3Ver = "1.2.1"
const val composeConstrLay = "1.0.1"
const val composeLibVer = "1.5.2"
const val composeActivityVer = "1.6.1"
const val composeViewModelLifecycleVer = "2.5.1"
const val composeLivecycleVer = "2.6.1"
const val composeNavigationVer = "2.7.3"
const val composeNavAnimationVer = "0.31.2-alpha"


//androidMaterialw
const val materialVer = "1.10.0"
const val constraintLayoutVer = "2.1.3"
const val materialDateTimePickerVer = "4.2.1"

//navigation
const val navigationVer = "2.5.3"

//kotlin
const val kotlinVer = "1.9.0"
const val ktxVer = "1.12.0"
const val kspVer = "1.9.0-1.0.13"
const val coroutinesVer = "1.4.2"
const val kotlinCompilerExtensionVer = "1.5.2"

//tests
const val junitVer = "4.12"
const val junitTestVer = "1.1.5"
const val espressoVer = "3.5.1"
const val kaspressoVer = "1.2.0"

//dagger
const val daggerVer = "2.50"
const val jsr250Ver = "1.0"

//room
const val roomVer = "2.6.1"

//JavaRx
const val rxJavaVer = "2.2.19"
const val rxAndroidVer = "2.1.1"

const val rxLintVer = "1.7.7"
const val rxJava2ExtentionVer = "0.20.10"

//lottie
const val lottieVer = "6.0.0"

//network
const val squareupOkhttpVer = "4.11.0"
const val retrofitVer = "2.9.0"

//picasso
const val squareupPicassoVer = "2.71828"

//video
const val youtubeVer = "1.2.2"
const val exoplayerVer = "2.11.8"

//lint
const val lintVer = "26.5.1"

//websocket
const val websocketVer = "2.6"

//androidX
const val lifecycleVmVer = "2.6.2"
const val lifecycleVer = "2.6.2"
const val appcompat = "1.6.1"


const val fragmentsXVer = "1.2.5"
const val legacyVer = "1.0.0"

//common
const val timberVer = "4.7.1"

val androidXLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("timber", Pair("com.jakewharton.timber:timber:$timberVer", "implementation"))
	put(
		"lifecycleVmKtx",
		Pair("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVmVer", "implementation")
	)
//	put(
//		"lifecycleExt",
//		Pair("androidx.lifecycle:lifecycle-extensions:$lifecycleExtVer", "implementation")
//	)
	put("lifecycleRuntime", Pair("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVer", "implementation"))
	put("lifecycleViewModel", Pair("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVer", "implementation"))
	put("fragmentsX", Pair("androidx.fragment:fragment-ktx:$fragmentsXVer", "implementation"))
	put("legacy", Pair("androidx.legacy:legacy-support-v4:$legacyVer", "implementation"))
	put(
		"constraintlayout",
		Pair("androidx.constraintlayout:constraintlayout:$constraintLayoutVer", "implementation")
	)
	put("appCompat", Pair("androidx.appcompat:appcompat:$appcompat", "implementation"))

}

val retrofitAndOkHttpLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	//retrofit
	put("core", Pair("com.squareup.retrofit2:retrofit:$retrofitVer", "implementation"))
	put("gson", Pair("com.squareup.retrofit2:converter-gson:$retrofitVer", "implementation"))
	put("moshi", Pair("com.squareup.retrofit2:converter-moshi:$retrofitVer", "implementation"))
	put("rxJava2", Pair("com.squareup.retrofit2:adapter-rxjava2:$retrofitVer", "implementation"))
	put("mock", Pair("com.squareup.retrofit2:retrofit-mock:$retrofitVer", "testImplementation"))
	put("scalar", Pair("com.squareup.retrofit2:converter-scalars:$retrofitVer", "implementation"))
	//OkHttp3
	put("okHttp", Pair("com.squareup.okhttp3:okhttp:$squareupOkhttpVer", "implementation"))
	put(
		"loggingInterceptor",
		Pair("com.squareup.okhttp3:logging-interceptor:$squareupOkhttpVer", "implementation")
	)
	put(
		"urlConnection",
		Pair("com.squareup.okhttp3:okhttp-urlconnection:$squareupOkhttpVer", "implementation")
	)
}

val androidMaterial = mutableMapOf<String, Pair<Any, String>>().apply {
	put("material", Pair("com.google.android.material:material:$materialVer", "implementation"))
	put(
		"constraintLayout",
		Pair("androidx.constraintlayout:constraintlayout:$constraintLayoutVer", "implementation")
	)
	put(
		"dateTimePicker",
		Pair("com.github.wdullaer:MaterialDateTimePicker:$materialDateTimePickerVer", "implementation")
	)
}

val kotlinLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("kotlin", Pair("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVer", "implementation"))
	put("ktx", Pair("androidx.core:core-ktx:$ktxVer", "implementation"))

	put(
		"coroutines",
		Pair("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVer", "implementation")
	)
	put(
		"coroutinesAndroid",
		Pair("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVer", "implementation")
	)
	put(
		"coroutinesTest",
		Pair("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVer", "testImplementation")
	)
	put("kotReflect", Pair("org.jetbrains.kotlin:kotlin-reflect:$kotlinVer", "implementation"))
}

val testLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("junit", Pair("junit:junit:$junitVer", "testImplementation"))
	put("junitAndroid", Pair("androidx.test.ext:junit:$junitTestVer", "androidTestImplementation"))
	put(
		"espresso",
		Pair("androidx.test.espresso:espresso-core:$espressoVer", "androidTestImplementation")
	)
//	put(
//		"kaspresso",
//		Pair("com.kaspersky.android-components:kaspresso:$kaspressoVer", "testImplementation")
//	)
}

val diLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("jsr250", Pair("javax.annotation:jsr250-api:$jsr250Ver", "compileOnly"))
	put("dagger", Pair("com.google.dagger:dagger:$daggerVer", "implementation"))
	put("daggerCompiler", Pair("com.google.dagger:dagger-compiler:$daggerVer", "ksp"))
	put("daggerAndroid", Pair("com.google.dagger:dagger-android:$daggerVer", "implementation"))
//	put(
//		"daggerProcessorAndroid",
//		Pair("com.google.dagger:dagger-android-processor:$daggerVer", "ksp")
//	)
}

val navigationLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put(
		"navFragment",
		Pair("androidx.navigation:navigation-fragment:$navigationVer", "implementation")
	)
	put("navUi", Pair("androidx.navigation:navigation-ui:$navigationVer", "implementation"))
	put(
		"navFragmentKtx",
		Pair("androidx.navigation:navigation-fragment-ktx:$navigationVer", "implementation")
	)
	put("navUiKtx", Pair("androidx.navigation:navigation-ui-ktx:$navigationVer", "implementation"))
	put(
		"navDynamicFragment",
		Pair(
			"androidx.navigation:navigation-dynamic-features-fragment:$navigationVer",
			"implementation"
		)
	)
	put(
		"navTesting",
		Pair("androidx.navigation:navigation-testing:$navigationVer", "androidTestImplementation")
	)
}

val composeLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put(
		"constrainlayout",
		Pair(
			"androidx.constraintlayout:constraintlayout-compose:$composeConstrLay",
			"implementation"
		)
	)
	put(
		"compMaterial3",
		Pair("androidx.compose.material3:material3:$composeMaterial3Ver", "implementation")
	)
	put(
		"compUiToolingPreview",
		Pair("androidx.compose.ui:ui-tooling-preview:$composeLibVer", "implementation")
	)
	put(
		"compUiTooling",
		Pair("androidx.compose.ui:ui-tooling:$composeLibVer", "debugImplementation")
	)
	put(
		"compActivity",
		Pair("androidx.activity:activity-compose:$composeActivityVer", "implementation")
	)
	put(
		"compViewModelLifecycle",
		Pair(
			"androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelLifecycleVer",
			"implementation"
		)
	)
	put(
		"compLivedata",
		Pair("androidx.compose.runtime:runtime-livedata:$composeLibVer", "implementation")
	)
	put(
		"compLiveCycle",
		Pair("androidx.lifecycle:lifecycle-runtime-compose:$composeLivecycleVer", "implementation")
	)
	put("compUi", Pair("androidx.compose.ui:ui:$composeLibVer", "implementation"))
	put(
		"compFoundation",
		Pair("androidx.compose.foundation:foundation:$composeLibVer", "implementation")
	)
	put(
		"compNavigation",
		Pair("androidx.navigation:navigation-compose:$composeNavigationVer", "implementation")
	)
	put(
		"compNavAnim",
		Pair(
			"com.google.accompanist:accompanist-navigation-animation:$composeNavAnimationVer",
			"implementation"
		)
	)
}

val javaRxLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("rxJava", Pair("io.reactivex.rxjava2:rxjava:$rxJavaVer", "implementation"))
	put("rxAndroid", Pair("com.google.dagger:dagger:$rxAndroidVer", "implementation"))
	put("rxLint", Pair("nl.littlerobots.rxlint:rxlint:$rxLintVer", "implementation"))
	put(
		"rxJava2Extention",
		Pair("com.github.akarnokd:rxjava2-extensions:$rxJava2ExtentionVer", "implementation")
	)

}

val roomLibs = mutableMapOf<String, Pair<Any, String>>().apply {
	put("roomRuntime", Pair("androidx.room:room-runtime:$roomVer", "implementation"))
	put("roomCompiler", Pair("androidx.room:room-compiler:$roomVer", "ksp"))
	put("roomKtx", Pair("androidx.room:room-ktx:$roomVer", "implementation"))
}

val exoplayer = mutableMapOf<String, Pair<Any, String>>().apply {
	put(
		"exoPlayerCore",
		Pair("com.google.android.exoplayer:exoplayer-core:$exoplayerVer", "implementation")
	)
	put(
		"exoPlayerUi",
		Pair("com.google.android.exoplayer:exoplayer-ui:$exoplayerVer", "implementation")
	)
	put(
		"exoPlayerHls",
		Pair("com.google.android.exoplayer:exoplayer-hls:$exoplayerVer", "implementation")
	)
	put(
		"exoPlayerOkHttp",
		Pair("com.google.android.exoplayer:extension-okhttp:$exoplayerVer", "implementation")
	)
}

val lint = mutableMapOf<String, Pair<Any, String>>().apply {
	put("api", Pair("com.android.tools.lint:lint-api:$lintVer", "implementation"))
	put("checks", Pair("com.android.tools.lint:lint-checks:$lintVer", "implementation"))
	put("tests", Pair("com.android.tools.lint:lint-tests:$lintVer", "implementation"))
}

val lottie = Pair<Any, String>("com.airbnb.android:lottie:$lottieVer", "implementation")
val squareupPicasso =
	Pair<Any, String>("com.squareup.picasso:picasso:$squareupPicassoVer", "implementation")
val youtube =
	Pair<Any, String>("com.google.youtube:YouTubeAndroidPlayerApi:$youtubeVer", "implementation")
val websocket =
	Pair<Any, String>("com.neovisionaries:nv-websocket-client:$websocketVer", "implementation")


/**
 * Impl work as implementation operator
 *
 * @param depAndType Pair<String as "androidx.core:core-ktx:$ktxVer", String as "implementation">
 * @return [Dependency]
 */
fun Project.impl(depAndType: Pair<Any, String>): Dependency? {
	return this.dependencies.add(depAndType.second, depAndType.first)
}

/**
 * setting FileTree dependency
 *
 * @param args Map<String as "dir" to "libs", * as "include" to listOf("*.jar")>
 * @return [ConfigurableFileTree]
 *
 */
fun Project.fileTreeFromConfig(args: Map<String, *>): ConfigurableFileTree? = this.fileTree(args)

/**
 * Impl map with libs
 * @param map build from put("rxJava", Pair("io.reactivex.rxjava2:rxjava:$rxJavaVer", "implementation")) instructions
 * @return dependencies
 *
 */
fun Project.implMap(map: Map<String, Pair<Any, String>>) {
	map.values.forEach { impl(it) }
}

fun Project.initLibDependencies() {

	dependencies {
		fileTreeFromConfig(mapOf("dir" to "libs", "include" to listOf("*.jar")))!!
		implMap(androidXLibs)
		implMap(androidMaterial)
		implMap(kotlinLibs)
		implMap(testLibs)
		implMap(diLibs)
		implMap(composeLibs)
		implMap(retrofitAndOkHttpLibs)
		//implMap(navigationLibs)
	}
}


