package com.example.c_feature_impl.di

import com.example.c_feature_impl.displays.c_feature_main.CFeatureViewModel
import com.example.common.FeatureModuleScope
import com.example.common.compose.ComposablePatchData
import com.example.common.di.LazyController
import dagger.Component

@Component(modules = [CFeatureModule::class])
@FeatureModuleScope
interface CFeatureComponent {
	//@Named("ComposeRoots")
	fun fileExporters1(): Map<String, ComposablePatchData>
	fun getViewModel(): CFeatureViewModel


	companion object {
		private var instance = LazyController<CFeatureComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> CFeatureComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}

}