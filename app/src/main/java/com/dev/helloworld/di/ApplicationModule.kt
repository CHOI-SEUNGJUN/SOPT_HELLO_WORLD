package com.dev.helloworld.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * 나중에 쓸일이 있겠구니 만들어둠
 */

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext() : Context = context

}