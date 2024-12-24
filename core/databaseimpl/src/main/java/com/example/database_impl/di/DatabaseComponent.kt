package com.example.database_impl.di

import android.content.Context
import com.example.common.di.LazyControllerSingleton
import com.example.database_api.dao.MainListDao
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
interface DatabaseComponent {

	fun provideMainListDao(): MainListDao

	@Component.Builder
	abstract class Builder {

		abstract fun build(): DatabaseComponent

		@BindsInstance
		abstract fun insertAppContext(context: Context): Builder
	}


	companion object {
		private var instance = LazyControllerSingleton<DatabaseComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> DatabaseComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}
}
