package com.dev.helloworld.di

import com.dev.helloworld.ui.signin.SignInActivity
import com.dev.helloworld.ui.signup.SignUpActivity
import com.dev.helloworld.ui.some.SomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelModule::class,
    ApplicationModule::class
])
interface AppComponent {

    fun inject(activity: SignInActivity)
    fun inject(activity: SignUpActivity)
    fun inject(activity: SomeActivity)

}