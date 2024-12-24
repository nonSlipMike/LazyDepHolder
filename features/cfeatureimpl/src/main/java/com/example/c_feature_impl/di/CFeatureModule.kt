package com.example.c_feature_impl.di

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.c_feature_api.dto.C_FEATURE_PATCH_MASK
import com.example.c_feature_impl.displays.c_feature_main.CFeatureMainComposeScreen
import com.example.c_feature_impl.displays.c_feature_main.CFeatureViewModel
import com.example.c_feature_impl.repositories.Screen2Repository
import com.example.c_feature_impl.repositories.Screen2RepositoryImpl
import com.example.common.ARGS_NAME
import com.example.common.FeatureModuleScope
import com.example.common.compose.ComposablePatchData
import com.example.common.compose.ComposablePatchData.Transitions.Companion.DownTransitions
import com.example.common.compose.provideViewModelWithDependency
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module(
	includes = [CFeatureModule.Declarations::class]
)
object CFeatureModule {

	@Provides
	@IntoMap
	@StringKey(C_FEATURE_PATCH_MASK)
	fun getNavHostConfig1(): ComposablePatchData {
		return ComposablePatchData(
			C_FEATURE_PATCH_MASK,
			transitions = DownTransitions,
			arguments = listOf(navArgument(ARGS_NAME) { type = NavType.StringType }),
			content = { routeHandler ->
				{
					CFeatureMainComposeScreen(it.arguments?.getString(ARGS_NAME),
						routeHandler,
						provideViewModelWithDependency {
							CFeatureComponent.getInstance().getViewModel()
						})
				}
			}
		)
	}

	@Provides
	fun provideViewModel(repository: Screen2Repository): CFeatureViewModel =
		CFeatureViewModel(repository)

	@Module
	internal abstract class Declarations {
		@FeatureModuleScope
		@Binds
		abstract fun bindRepository(impl: Screen2RepositoryImpl): Screen2Repository
	}

}