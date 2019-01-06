package com.drury.twittermoodanalyzer.di.module

import android.app.Application
import com.drury.twittermoodanalyzer.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}