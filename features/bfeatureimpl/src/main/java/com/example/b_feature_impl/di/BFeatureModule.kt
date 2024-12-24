package com.example.b_feature_impl.di

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.b_feature_api.dto.B_FEATURE_PATCH
import com.example.b_feature_api.dto.B_FEATURE_PATCH_MASK
import com.example.b_feature_impl.displays.b_feature_main.BFeatureMainComposeScreen
import com.example.b_feature_impl.displays.b_feature_main.BFeatureViewModel
import com.example.common.ARGS_NAME
import com.example.common.compose.ComposablePatchData
import com.example.common.compose.provideViewModelWithDependency
import com.example.database_api.dao.MainListDao
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
object BFeatureModule {

	@Provides
	@IntoMap
	@StringKey(B_FEATURE_PATCH_MASK)
	fun getNavHostConfig1(): ComposablePatchData {
		return ComposablePatchData(
			B_FEATURE_PATCH_MASK,
			transitions = ComposablePatchData.Transitions.DownTransitions,
			arguments = listOf(navArgument(ARGS_NAME) { type = NavType.StringType }),
			content = { routeHandler ->
				{
					BFeatureMainComposeScreen(it.arguments?.getString(ARGS_NAME),
						routeHandler,
						provideViewModelWithDependency {
							BFeatureComponent.getInstance().getViewModel()
						})
				}
			}
		)
	}

	@Provides
	fun provideViewModel(dao: MainListDao): BFeatureViewModel =
		BFeatureViewModel(dao)


//	@Module
//	internal abstract class Declarations {
//		@FeatureModuleScope
//		@Binds
//		abstract fun bindRepository(impl: Screen2RepositoryImpl): Screen2Repository
//	}

}