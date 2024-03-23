package com.example.network_impl.di

import android.content.Context
import com.example.common.di.LazyControllerSingleton
import com.example.network_api.RetrofitProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {

	fun provideRetrofitClient(): RetrofitProvider

	@Component.Builder
	abstract class Builder {

		abstract fun build(): NetworkComponent

		@BindsInstance
		abstract fun insertAppContext(context: Context): Builder
	}


	companion object {
		private var instance = LazyControllerSingleton<NetworkComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> NetworkComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}
}
