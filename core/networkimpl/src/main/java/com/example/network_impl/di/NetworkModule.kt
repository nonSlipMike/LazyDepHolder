package com.example.network_impl.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.network_api.RetrofitProvider
import com.example.network_impl.impl.RetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module(includes = [NetworkModule.Declarations::class])
object NetworkModule {

	@Module
	internal abstract class Declarations {
		@Singleton
		@Binds
		abstract fun provideDispatcherProvider(retrofit: RetrofitImpl): RetrofitProvider
	}

}