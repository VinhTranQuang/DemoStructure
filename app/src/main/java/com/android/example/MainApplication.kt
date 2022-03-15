package com.android.example

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        fun get(activity: Activity): MainApplication? {
            return MainApplication::class.java.cast(activity.application)
        }

        fun get(service: Service): MainApplication? {
            return MainApplication::class.java.cast(service.application)
        }

    }
    val appLanguage = MutableLiveData<String>()

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
