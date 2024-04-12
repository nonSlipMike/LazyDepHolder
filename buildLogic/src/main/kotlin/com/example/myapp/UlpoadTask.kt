package com.example.myapp

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

abstract class UploadTask : DefaultTask() {

	@get:InputDirectory
	abstract val apkDirectory: DirectoryProperty

	@TaskAction
	fun upload() {
		apkDirectory.get().asFile.listFiles()
			.filter { it.name.endsWith(".apk") }
			.forEach {
				println(it.name)
				//тут выгружаем файл например ретрофитом или ktor
			}
	}
}