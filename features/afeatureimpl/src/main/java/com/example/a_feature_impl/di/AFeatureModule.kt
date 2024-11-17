package com.example.a_feature_impl.di

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.a_feature_api.network.A_FEATURE_PATCH_MASK
import com.example.a_feature_impl.displays.a_feature_main.AFeatureMainComposeScreen
import com.example.a_feature_impl.displays.a_feature_main.AFeatureRepository
import com.example.a_feature_impl.displays.a_feature_main.AFeatureRepositoryImpl
import com.example.a_feature_impl.displays.a_feature_main.AFeatureViewModel
import com.example.a_feature_impl.network.AFeatureInteractor
import com.example.a_feature_impl.network.AFeatureInteractorImpl
import com.example.a_feature_impl.network.AFeatureRest
import com.example.common.ARGS_NAME
import com.example.common.compose.ComposablePatchData
import com.example.common.compose.provideViewModelWithDependency
import com.example.network_api.RetrofitProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module(
	includes = [AFeatureModule.Declarations::class]
)
object AFeatureModule {

	@Provides
	fun provideRetrofitAFeatureRest(retrofitProvider: RetrofitProvider) =
		retrofitProvider.retrofit.create(AFeatureRest::class.java)


	@Provides
	@IntoMap
	@StringKey(A_FEATURE_PATCH_MASK)
	fun getNavHostConfig1(): ComposablePatchData {
		return ComposablePatchData(
			A_FEATURE_PATCH_MASK,
			transitions = ComposablePatchData.Transitions.DownTransitions,
			arguments = listOf(navArgument(ARGS_NAME) { type = NavType.StringType }),
			content = { routeHandler ->
				{
					AFeatureMainComposeScreen(it.arguments?.getString(ARGS_NAME),
						routeHandler,
						provideViewModelWithDependency {
							AFeatureComponent.getInstance().getViewModel()
						})
				}
			}
		)
	}

	@Provides
	fun provideViewModel(repository: AFeatureRepository): AFeatureViewModel =
		AFeatureViewModel(repository)

	@Module
	internal abstract class Declarations {
		@Binds
		abstract fun bindInteractor(impl: AFeatureInteractorImpl): AFeatureInteractor

		@Binds
		abstract fun bindRepository(impl: AFeatureRepositoryImpl): AFeatureRepository
	}
}