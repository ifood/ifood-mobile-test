package com.rlino.ifoodtwitterchallenge.di

import android.content.Context
import com.rlino.ifoodtwitterchallenge.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

}