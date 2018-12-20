package com.example.tweetanalyzer.di.component

import com.example.tweetanalyzer.TweetAnalyzerApplication
import com.example.tweetanalyzer.di.module.ApplicationModule
import com.example.tweetanalyzer.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(androidApplication: TweetAnalyzerApplication)
    fun inject(mainActivity: MainActivity)
}