package com.example.myapp

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * 1)осздать buildLogic дирректорию и подключить ее в setings.gradle.kts
 * includeBuild("buildLogic")
 * 2)добавить библиотеки buildLogic build.gradle.kts
 * implementation("com.android.tools.build:gradle:7.4.2")
 * implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
 * 3)зарегистрировать плагин buildLogic build.gradle.kts
 * gradlePlugin {
 *	plugins.register("upload-plugin") {
 *			id = "upload-plugin"
 *			implementationClass = "com.example.myapp.UploadPlugin"
 *		}
 *	}
 * 4)создать плагин и таску (учитывая трубопровод)
 * 5)объявить плагин в build.gradle.kts app модуля pligins(id(...))
 * 6)вызвать таску из консоли по имени из - project.tasks.register
 * 			./gradlew :app:uploadApkForDevDebug для данного случая
 * 					DevDebug имя сборки
 *
 */
class UploadPlugin : Plugin<Project> {

	override fun apply(project: Project) {
		val androidComponents =
			project.extensions.findByType(AndroidComponentsExtension::class.java)
				?: throw java.lang.IllegalStateException("android plugin not found")

		androidComponents.onVariants { variant ->
			val variantName = variant.name.capitalize()
			val pkDirFormVariant = variant.artifacts.get(SingleArtifact.APK)
			project.tasks.register("uploadApkFor$variantName", UploadTask::class.java){
				apkDirectory.set(pkDirFormVariant)
			}
		}
	}
}