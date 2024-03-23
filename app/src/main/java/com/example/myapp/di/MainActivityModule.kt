package com.example.myapp.di

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.example.common.BigBean
import com.example.composerootimpl.R
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey


@Module(
	includes = [MainActivityModule.Declarations::class]
)
object MainActivityModule {

	@RequiresApi(Build.VERSION_CODES.N)
	@Provides
	//медиатор для даггера (без этого он не инжектит мапу)
	fun asdadd1(routerMap: Map<String, (Bundle) -> Fragment>) : Map<String,((Bundle) -> Fragment)?> {
		val mediatorRouteMap = mutableMapOf<String,((Bundle) -> Fragment)?>()
		routerMap.forEach { (t, u) -> mediatorRouteMap[t] = u }
		return mediatorRouteMap
	}



	@Module
	internal abstract class Declarations {
//		@Binds
//		abstract fun provideAFeatureFragment(asd: Map<String, Int>):Map<String, Int>
	}

}