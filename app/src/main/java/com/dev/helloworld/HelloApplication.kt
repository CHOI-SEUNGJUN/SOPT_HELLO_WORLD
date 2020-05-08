package com.dev.helloworld

import android.app.Application
import com.dev.helloworld.di.AppComponent
import com.dev.helloworld.di.ApplicationModule
import com.dev.helloworld.di.DaggerAppComponent
import com.dev.helloworld.di.NetworkModule

open class HelloApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .applicationModule(ApplicationModule(this))
            .build()

    }
}