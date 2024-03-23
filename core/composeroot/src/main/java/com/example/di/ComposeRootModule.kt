package com.example.di

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.example.common.AppModuleScope
import com.example.common.BigBean
import com.example.composeroot.ComposeRootFragment
import com.example.composerootimpl.R
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Named


@Module(
	includes = [ComposeRootModule.Declarations::class]
)
object ComposeRootModule {

	@Provides
	@IntoMap
	@StringKey("feature/ComposeRootFragment")
	fun provideAFeatureFragment(): (Bundle?) -> Fragment =
		{ it -> ComposeRootFragment.getInstance().apply { arguments = it } }

//    @Provides
//    @IntoMap
//    @StringKey("feature/ComposeRootFragment")
//    fun provideAFeatureFragment(): String ="asd"
//        //ComposerootNavGraphDirections.actionGlobalComposeRootFragment(2)

	@Module
	internal abstract class Declarations {

	}

}