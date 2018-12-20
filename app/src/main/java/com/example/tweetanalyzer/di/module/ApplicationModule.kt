package com.example.tweetanalyzer.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.api.google.GoogleCNLApi
import com.example.tweetanalyzer.api.twitter.TwitterApi
import com.example.tweetanalyzer.api.twitter.TwitterInterceptor
import com.example.tweetanalyzer.di.qualifier.ApplicationContext
import com.example.tweetanalyzer.repository.google.GoogleRepository
import com.example.tweetanalyzer.repository.google.GoogleRepositoryImpl
import com.example.tweetanalyzer.repository.twitter.TwitterRepository
import com.example.tweetanalyzer.repository.twitter.TwitterRepositoryImpl
import com.example.tweetanalyzer.viewmodel.CustomViewModelFactory
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

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideCustomViewModelFactory(customViewModelFactory: CustomViewModelFactory) : ViewModelProvider.Factory{
        return customViewModelFactory
    }

    /*START PROVIDES SERVICES */
    @Provides
    @Singleton
    fun provideTwitterApi(twitterInterceptor: TwitterInterceptor): TwitterApi = TwitterApi(twitterInterceptor)

    @Provides
    @Singleton
    fun provideGoogleCNLApi(): GoogleCNLApi = GoogleCNLApi()


    /*START PROVIDES REPOSITORY */
    @Provides
    @Singleton
    fun provideTwitterRepository(twitterRepositoryImpl: TwitterRepositoryImpl) : TwitterRepository{
        return twitterRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGoogleRepository(googleRepositoryImpl: GoogleRepositoryImpl) : GoogleRepository{
        return googleRepositoryImpl
    }
}