package com.example.common.di

class LazyControllerSingleton<T> : LazyController<T>() {
	override fun getLazyInstance(): T {
		if (strongRefInstance == null) {
			strongRefInstance =
				lazy { setLazyInstanceFunction() }
		}
		return strongRefInstance!!.value
			?: throw IllegalArgumentException(
				"instance not yet initialized ( need to use method .setLazyInstance() first )"
			)
	}
}