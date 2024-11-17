package com.example.common

import javax.inject.Qualifier
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FeatureModuleScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppModuleScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MyModuleScope

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BigBean