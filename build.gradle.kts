plugins {
	id("com.android.application") version gradleConfVer apply false
	id("com.android.library") version gradleConfVer apply false
	id("org.jetbrains.kotlin.android") version kotlinVer apply false
	id("com.google.devtools.ksp") version kspVer apply false
	id("androidx.navigation.safeargs") version navigationVer apply false
	id("androidx.room") version roomVer apply false
	id("org.jetbrains.compose") version composeLibVer apply false
	id("org.jetbrains.kotlin.jvm") version kotlinVer
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

gradle.taskGraph.whenReady(closureOf<TaskExecutionGraph> {
	allTasks.forEach { task ->
		if (task.name.contains("lint")) {
			task.enabled = false
			logger.log(LogLevel.WARN, "${task.name} has been disabled")
		}
	}
})


