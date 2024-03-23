package com.example.myapp.app

import com.example.myapp.BuildConfig
import timber.log.Timber

object LoggerHandler {
    fun initTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}