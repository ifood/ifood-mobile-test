package com.drury.twittermoodanalyzer

import android.app.Application
import com.drury.twittermoodanalyzer.di.component.ApplicationComponent
import com.drury.twittermoodanalyzer.di.component.DaggerApplicationComponent
import com.drury.twittermoodanalyzer.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
        }

    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}