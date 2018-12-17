package com.example.tweetanalyzer

import android.app.Application
import com.example.tweetanalyzer.di.component.ApplicationComponent
import com.example.tweetanalyzer.di.component.DaggerApplicationComponent
import com.example.tweetanalyzer.di.module.ApplicationModule

class TweetAnalyzerApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}