package com.example.a_feature_impl.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.a_feature_impl.displays.a_feature_main.AFeatureViewModel
import com.example.common.di.LazyController
import com.example.network_api.RetrofitProvider
import dagger.BindsInstance
import dagger.Component

@Component( modules = [AFeatureModule::class])
interface AFeatureComponent {

	fun provideViewModel( ): AFeatureViewModel.Factory

	fun getFragmentPatches(): Map<String, (Bundle?) -> Fragment>

	@Component.Builder
	abstract class Builder {

		abstract fun build(): AFeatureComponent

		@BindsInstance
		abstract fun insertNetworkClient(retrofit: RetrofitProvider): Builder
	}


	companion object {
		private var instance = LazyController<AFeatureComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> AFeatureComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}
}
