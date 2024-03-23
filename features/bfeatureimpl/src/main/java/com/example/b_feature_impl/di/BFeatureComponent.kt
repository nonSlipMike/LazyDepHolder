package com.example.b_feature_impl.di

import com.example.b_feature_impl.displays.b_feature_main.BFeatureViewModel
import com.example.common.FeatureModuleScope
import dagger.Component

@Component( modules = [BFeatureModule::class])
@FeatureModuleScope
 interface BFeatureComponent  {

   fun inject(viewModel: BFeatureViewModel)

//    companion object {
//        fun initAndGet(commonDependencies: BFeatureDependencies): BFeatureComponent {
//            return DaggerBFeatureComponent.builder()
//                .bFeatureDependencies(commonDependencies)
//                .build()
//        }
//    }
}
