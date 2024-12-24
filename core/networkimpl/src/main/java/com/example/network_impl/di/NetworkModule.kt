package com.example.network_impl.di

import com.example.network_api.RetrofitProvider
import com.example.network_impl.impl.RetrofitProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule.Declarations::class])
object NetworkModule {

	@Module
	internal abstract class Declarations {
		@Singleton
		@Binds
		abstract fun provideNetworkProvider(retrofit: RetrofitProviderImpl): RetrofitProvider
	}

}