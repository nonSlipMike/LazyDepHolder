import java.net.URI

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
		maven { url = URI.create("https://jitpack.io") } // JitPack
	}
}

rootProject.name = "MyApp"
include(":app")
include(":common")

include(":features:afeatureimpl")
include(":features:afeatureapi")
include(":features:bfeatureimpl")
include(":features:bfeatureapi")
include(":features:cfeatureimpl")
include(":features:cfeatureapi")

include(":core:databaseimpl")
include(":core:databaseapi")
include(":core:networkimpl")
include(":core:networkapi")
