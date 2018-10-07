package com.test.ifood.twitterhumour.app.di

import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.TweetListActivity
import com.test.ifood.twitterhumour.tweetlist.tweetsoverview.di.TweetListActivityModule
import com.test.ifood.twitterhumour.welcome.WelcomeActivity
import com.test.ifood.twitterhumour.welcome.di.WelcomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinding {

    @ContributesAndroidInjector(modules = [WelcomeActivityModule::class])
    abstract fun bindWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector(modules = [TweetListActivityModule::class])
    abstract fun bindTweetListActivity(): TweetListActivity
}