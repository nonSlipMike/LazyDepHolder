package com.example.myapp.di

import dagger.Module

@Module(
	includes = [MainActivityModule.Declarations::class]
)
object MainActivityModule {

	@Module
	internal abstract class Declarations {}

}