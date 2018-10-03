package com.test.ifood.twitterhumour.app.di

import com.test.ifood.twitterhumour.welcome.WelcomeActivity
import com.test.ifood.twitterhumour.welcome.di.WelcomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinding {

    @ContributesAndroidInjector(modules = [WelcomeActivityModule::class])
    abstract fun bindWelcomeActivity(): WelcomeActivity
}