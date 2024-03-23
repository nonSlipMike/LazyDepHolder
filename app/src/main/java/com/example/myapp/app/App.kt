package com.example.myapp.app

import android.app.Application
import com.example.myapp.di.DaggerComponentsInitializer.daggerComponentsInit

class App : Application() {


	override fun onCreate() {
		super.onCreate()
		LoggerHandler.initTimber()
		createNotificationChannel()
		daggerComponentsInit(this)
	}


}