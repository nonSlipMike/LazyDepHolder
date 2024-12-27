package com.example.c_feature_impl.di

import com.example.c_feature_impl.displays.c_feature_main.CFeatureViewModel
import com.example.common.FeatureModuleScope
import com.example.common.compose.ComposablePatchData
import com.example.common.di.LazyController
import com.example.database_api.dao.MainListDao
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CFeatureModule::class])
@FeatureModuleScope
interface CFeatureComponent {
	fun fileExporters(): Map<String, ComposablePatchData>
	fun getViewModel(): CFeatureViewModel

	@Component.Builder
	abstract class Builder {

		abstract fun build(): CFeatureComponent

		@BindsInstance
		abstract fun insertMainListDao(dao: MainListDao): Builder
	}



	companion object {
		private var instance = LazyController<CFeatureComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> CFeatureComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}

}