package com.drury.twittermoodanalyzer.di.component

import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.di.module.ActivityModule
import com.drury.twittermoodanalyzer.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(
            ApplicationModule::class,
            ActivityModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)
}