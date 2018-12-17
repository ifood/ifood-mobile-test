package com.example.tweetanalyzer.di.module

import android.app.Application
import android.content.Context
import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.api.google.GoogleCNLApi
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: TweetAnalyzerApplication) {
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application = application

    /*START PROVIDES SERVICES */
    @Provides
    @Singleton
    fun provideTwitterApi(): TwitterApi {
        return TwitterApi()
    }

    @Provides
    @Singleton
    fun provideGoogleCNLApi(): GoogleCNLApi {
        return GoogleCNLApi()
    }
}