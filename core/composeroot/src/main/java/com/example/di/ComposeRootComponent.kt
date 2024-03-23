package com.example.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.common.MyModuleScope
import com.example.common.compose.ComposablePatchData
import com.example.common.di.LazyController
import com.example.composeroot.ComposeRootFragment
import dagger.BindsInstance
import dagger.Component

@Component(
	//dependencies = [CFeatureComponent::class],
	modules = [ComposeRootModule::class]
)
@MyModuleScope
interface ComposeRootComponent {
	fun inject(composeFragment: ComposeRootFragment)

	fun getFragmentPatches(): Map<String, (Bundle?) -> Fragment >

	@Component.Builder
	abstract class Builder {

		abstract fun build(): ComposeRootComponent

		@BindsInstance
		abstract fun insertRoutes(routerMap: Map<String, ComposablePatchData>): Builder
	}

	companion object {
		private var instance = LazyController<ComposeRootComponent>()
		fun getInstance() = instance.getLazyInstance()
		fun setInstance(setLazyInstanceFunction: () -> ComposeRootComponent) =
			instance.setLazyInstance { setLazyInstanceFunction() }
	}
}
