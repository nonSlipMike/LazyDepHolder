pluginManagement {
	includeBuild("buildLogic")
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "MyApp"
include(":app")

include(":features:afeatureimpl")
include(":features:afeatureapi")
include(":features:bfeatureimpl")
include(":features:bfeatureapi")
include(":features:cfeatureimpl")
include(":features:cfeatureapi")

include(":common")
include(":core:database")
include(":core:networkimpl")
include(":core:networkapi")