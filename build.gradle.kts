import com.android.build.gradle.BaseExtension

plugins {
    id("com.google.devtools.ksp") version kspVer apply false
    id("com.android.application") version gradleConfVer apply false
    id("com.android.library") version gradleConfVer apply false
    id("org.jetbrains.kotlin.android") version kotlinVer apply false
    id("androidx.navigation.safeargs") version navigationVer apply false
    id("androidx.room") version roomVer apply false
    id("org.jetbrains.kotlin.plugin.compose") version kotlinVer apply false
    id("org.jetbrains.kotlin.jvm") version kotlinVer
}


subprojects {
    listOf(
        "org.jetbrains.kotlin.android" to org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension::class,
    ).forEach { (pluginId, extensionClass) ->
        plugins.withId(pluginId) {
            project.initLibDependencies()
            extensions.configure<BaseExtension> {
                compileSdkVersion = "android-${compileSdkVersionConf}"
                buildFeatures.buildConfig = true
            }
            extensions.findByType(extensionClass.java)?.apply {
                jvmToolchain {
                    languageVersion.set(JavaLanguageVersion.of(17))
                }
            }
            if (plugins.hasPlugin("org.jetbrains.kotlin.plugin.compose")) {
                println("Compose плагин подключён для модуля: ${project.name}")
                // Дополнительные действия, если Compose найден
                extensions.configure<BaseExtension> {
                    buildFeatures.compose = true
                    composeOptions.kotlinCompilerExtensionVersion = kotlinCompilerExtensionVer
                }
            }
        }
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


