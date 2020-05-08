package com.dev.helloworld.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.dev.helloworld.HelloApplication
import com.dev.helloworld.di.AppComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected val appComponent: AppComponent by lazy {
        (application as HelloApplication).appComponent
    }

}