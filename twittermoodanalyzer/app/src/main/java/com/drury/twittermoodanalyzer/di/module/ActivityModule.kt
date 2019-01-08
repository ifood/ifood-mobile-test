package com.drury.twittermoodanalyzer.di.module

import android.app.Activity
import com.drury.twittermoodanalyzer.Interfaces
import com.drury.twittermoodanalyzer.presenter.MainPresenter
import com.drury.twittermoodanalyzer.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

}