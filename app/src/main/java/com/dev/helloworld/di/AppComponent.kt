package com.dev.helloworld.di

import com.dev.helloworld.ui.signin.SignInActivity
import com.dev.helloworld.ui.signup.SignUpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    ViewModelModule::class,
    ApplicationModule::class
])
interface AppComponent {

    fun inject(activity: SignInActivity)
    fun inject(activity: SignUpActivity)

}