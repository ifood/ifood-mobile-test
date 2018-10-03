package com.test.ifood.twitterhumour.welcome.di

import android.arch.lifecycle.ViewModelProviders
import com.test.ifood.twitterhumour.welcome.WelcomeActivity
import com.test.ifood.twitterhumour.welcome.viewmodel.WelcomeViewModel
import dagger.Module
import dagger.Provides

@Module
class WelcomeActivityModule {

    @Provides
    fun providesWelcomeViewModel(activity: WelcomeActivity): WelcomeViewModel {
        return ViewModelProviders.of(activity).get(WelcomeViewModel::class.java)
    }
}