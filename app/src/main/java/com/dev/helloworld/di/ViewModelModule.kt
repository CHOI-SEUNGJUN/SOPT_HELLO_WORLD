package com.dev.helloworld.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.helloworld.api.NetworkRepository
import com.dev.helloworld.ui.signin.SignInViewModel
import com.dev.helloworld.ui.signup.SignUpViewModel
import com.dev.helloworld.util.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ViewModelKey(val value: KClass<out ViewModel>)


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun provideSignInModel(signInViewModel: SignInViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun provideSignUpModel(signUpViewModel: SignUpViewModel) : ViewModel
}