package com.example.myapp.di

import com.example.common.compose.ComposablePatchData
import com.example.common.di.LazyController
import com.example.myapp.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
	modules = [MainActivityModule::class]
)
interface MainActivityComponent {

	fun inject(activity: MainActivity)

	@Component.Builder
	abstract class Builder {

		abstract fun build(): MainActivityComponent

		@BindsInstance
		abstract fun insertRoutes(routerMap: Map<String, ComposablePatchData>): Builder
	}

	companion object {
		private var instance = LazyController<MainActivityComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> MainActivityComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }

	}
}
