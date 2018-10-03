package com.test.ifood.twitterhumour.app.di

import android.app.Application
import com.test.ifood.twitterhumour.app.App
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplication(app: App): Application

}