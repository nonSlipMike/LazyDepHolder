package com.example.database.di


import dagger.Component
import javax.inject.Singleton

@Component( modules = [DatabaseModule::class])
@Singleton
internal interface DatabaseComponent  {

//    companion object {
//        fun initAndGet(databaseDependencies: DatabaseFeatureDependencies): DatabaseComponent {
//            return DaggerDatabaseComponent.builder()
//                .databaseFeatureDependencies(databaseDependencies)
//                .build()
//        }
//    }
}
