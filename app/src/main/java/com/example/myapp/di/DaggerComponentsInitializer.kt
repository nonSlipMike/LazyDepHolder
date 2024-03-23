package com.example.myapp.di

import android.content.Context
import com.example.a_feature_impl.di.AFeatureComponent
import com.example.a_feature_impl.di.DaggerAFeatureComponent
import com.example.c_feature_impl.di.CFeatureComponent
import com.example.c_feature_impl.di.DaggerCFeatureComponent
import com.example.common.di.getOneMap
import com.example.di.ComposeRootComponent
import com.example.di.DaggerComposeRootComponent
import com.example.network_impl.di.DaggerNetworkComponent
import com.example.network_impl.di.NetworkComponent

object DaggerComponentsInitializer {
	fun daggerComponentsInit(context: Context) {
		NetworkComponent.setInstance {
			DaggerNetworkComponent.builder()
				.insertAppContext(context)
				.build()
		}

		CFeatureComponent.setInstance { DaggerCFeatureComponent.builder().build() }

		AFeatureComponent.setInstance {
			DaggerAFeatureComponent.builder()
				.insertNetworkClient(NetworkComponent.getInstance().provideRetrofitClient())
				.build()
		}

		ComposeRootComponent.setInstance {
			DaggerComposeRootComponent.builder().insertRoutes(
				//тут подключаются пути для новый композ дисплеев
				arrayListOf(CFeatureComponent.getInstance().fileExporters1()).getOneMap()
			).build()
		}

		MainActivityComponent.setInstance {
			DaggerMainActivityComponent.builder()
				.insertRoutes(
					//тут подключаются пути для новых фрагментов
					arrayListOf(
						ComposeRootComponent.getInstance().getFragmentPatches(),
						AFeatureComponent.getInstance().getFragmentPatches()
					).getOneMap()
				).build()
		}
	}
}



