plugins{
    id ("com.android.application") version gradleConfVer apply false
    id ("com.android.library") version gradleConfVer apply false
    id ("org.jetbrains.kotlin.android") version kotlinVer apply false
    id ("org.jetbrains.kotlin.kapt") version kotlinVer apply false
    id ("androidx.navigation.safeargs") version navigationVer apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

gradle.taskGraph.whenReady(closureOf<TaskExecutionGraph> {
    allTasks.forEach { task ->
        if (task.name.contains("lint")) {
            task.enabled = false
            logger.log(LogLevel.WARN, "${task.name} has been disabled")
        }
    }
})


