package com.dev.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_some)
    }
}