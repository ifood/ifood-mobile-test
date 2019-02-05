package com.drss.ifoodmobiletest

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class iFoodMobileTestApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().inject(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        setupTwitter()
    }

    fun setupTwitter() {
        val twitterConfig = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .debug(BuildConfig.DEBUG)
            .twitterAuthConfig(TwitterAuthConfig(BuildConfig.twitterConsumerKey, BuildConfig.twitterConsumerSecret))
            .build()
        Twitter.initialize(twitterConfig)
    }

}