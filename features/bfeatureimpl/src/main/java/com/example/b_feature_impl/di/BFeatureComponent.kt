package com.example.b_feature_impl.di

import android.content.Context
import com.example.b_feature_impl.displays.b_feature_main.BFeatureViewModel
import com.example.common.FeatureModuleScope
import com.example.common.compose.ComposablePatchData
import com.example.common.di.LazyController
import com.example.database_api.dao.MainListDao
import dagger.BindsInstance
import dagger.Component

@Component( modules = [BFeatureModule::class])
@FeatureModuleScope
interface BFeatureComponent  {

    fun fileExporters(): Map<String, ComposablePatchData>
    fun getViewModel(): BFeatureViewModel

    @Component.Builder
    abstract class Builder {

        abstract fun build(): BFeatureComponent

        @BindsInstance
        abstract fun insertMainListDao(dao: MainListDao): Builder
    }


    companion object {
        private var instance = LazyController<BFeatureComponent>()
        fun getInstance() = instance.getLazyInstance()
        fun setInstance(setLazyInstanceFunction: () -> BFeatureComponent) =
            instance.setLazyInstance { setLazyInstanceFunction() }
    }
}
