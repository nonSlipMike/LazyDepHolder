import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class FindUnusedClassesTask : DefaultTask() {

	@get:Input
	abstract var mappingFilePath: String

	@TaskAction
	fun execute() {
		val mappingFile = File(mappingFilePath)
		if (!mappingFile.exists()) {
			throw GradleException("Mapping file not found: $mappingFilePath")
		}

		println("Using mapping file: $mappingFilePath")

		// Чтение mapping.txt
		val classNames = mutableSetOf<String>()
		mappingFile.forEachLine { line ->
			if (line.startsWith("    ")) {
				val className = line.trim().split(" -> ").firstOrNull()?.replace("/", ".")
				if (!className.isNullOrEmpty()) {
					classNames.add(className)
				}
			}
		}

		if (classNames.isEmpty()) {
			println("No classes found in mapping file.")
			return
		}

		println("Classes found in mapping file: ${classNames.size}")
		println("Unused classes (sample logic):")
		classNames.filter { it.contains("Unused") }.forEach { println(it) }
	}
}

// Регистрация задачи
tasks.register<FindUnusedClassesTask>("findUnusedClasses") {
	group = "build"
	description = "Find unused classes based on mapping.txt"

	// Указываем путь к mapping.txt на этапе конфигурации
	mappingFilePath = "${buildDir}/outputs/mapping/prodRelease/mapping.txt"
}


// ./gradlew :app:findUnusedClasses --configuration-cache
// чтобы увидеть неиспользуемые классы