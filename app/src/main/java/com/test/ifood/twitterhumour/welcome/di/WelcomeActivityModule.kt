package com.test.ifood.twitterhumour.welcome.di

import android.arch.lifecycle.ViewModelProviders
import com.test.ifood.twitterhumour.welcome.WelcomeActivity
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import com.test.ifood.twitterhumour.welcome.viewmodel.factory.WelcomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class WelcomeActivityModule {

    @Provides
    fun providesWelcomeViewModel(activity: WelcomeActivity, factory: WelcomeViewModelFactory): WelcomeViewModel {
        return ViewModelProviders.of(activity, factory).get(WelcomeViewModel::class.java)
    }
}