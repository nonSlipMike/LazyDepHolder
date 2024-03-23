package com.example.a_feature_impl.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.a_feature_impl.displays.a_feature_main.AFeatureFragment
import com.example.a_feature_impl.displays.a_feature_main.AFeatureRepository
import com.example.a_feature_impl.displays.a_feature_main.AFeatureRepositoryImpl
import com.example.a_feature_impl.network.AFeatureInteractor
import com.example.a_feature_impl.network.AFeatureInteractorImpl
import com.example.a_feature_impl.network.AFeatureRest
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
	@StringKey("feature/AFeatureFragment")
	fun provideAFeatureFragment(): (Bundle?) -> Fragment =
		{ it -> AFeatureFragment.getInstance().apply { arguments = it } }


	@Module
	internal abstract class Declarations {
		@Binds
		abstract fun bindInteractor(impl: AFeatureInteractorImpl): AFeatureInteractor

		@Binds
		abstract fun bindRepository(impl: AFeatureRepositoryImpl): AFeatureRepository
	}
}