package com.dev.helloworld

import android.app.Application
import com.dev.helloworld.di.AppComponent
import com.dev.helloworld.di.ApplicationModule
import com.dev.helloworld.di.DaggerAppComponent
import timber.log.Timber

open class HelloApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initComponent()
        initLogger()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

}