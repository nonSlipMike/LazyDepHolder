package com.example.myapp.di

import android.content.Context
import com.example.a_feature_impl.di.AFeatureComponent
import com.example.a_feature_impl.di.DaggerAFeatureComponent
import com.example.b_feature_impl.di.BFeatureComponent
import com.example.b_feature_impl.di.DaggerBFeatureComponent
import com.example.c_feature_impl.di.CFeatureComponent
import com.example.c_feature_impl.di.DaggerCFeatureComponent
import com.example.common.di.getOneMap
import com.example.network_impl.di.DaggerNetworkComponent
import com.example.network_impl.di.NetworkComponent

// если стреляет "lateinit property setLazyInstanceFunction"
// значит ты забыл указать новый компонент
object DaggerComponentsInitializer {
	fun daggerComponentsInit(context: Context) {
		NetworkComponent.setInstance {
			DaggerNetworkComponent.builder()
				.insertAppContext(context)
				.build()
		}

		AFeatureComponent.setInstance {
			DaggerAFeatureComponent.builder()
				.insertNetworkClient(NetworkComponent.getInstance().provideRetrofitClient())
				.build()
		}
		BFeatureComponent.setInstance { DaggerBFeatureComponent.builder().build() }
		CFeatureComponent.setInstance { DaggerCFeatureComponent.builder().build() }


		MainActivityComponent.setInstance {
			DaggerMainActivityComponent.builder()
				.insertRoutes(
					//тут подключаются пути для новый композ дисплеев
					arrayListOf(
						AFeatureComponent.getInstance().fileExporters(),
						CFeatureComponent.getInstance().fileExporters(),
						BFeatureComponent.getInstance().fileExporters()
					).getOneMap()
				).build()
		}
	}
}



