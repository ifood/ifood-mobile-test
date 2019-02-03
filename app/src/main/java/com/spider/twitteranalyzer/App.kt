package com.spider.twitteranalyzer

import android.app.Activity
import com.spider.twitteranalyzer.injection.AppComponent
import com.spider.twitteranalyzer.injection.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication


open class App : DaggerApplication() {
    var activityInjector: DispatchingAndroidInjector<Activity>? = null

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector ?: super.activityInjector()
    }

    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.builder().create(this) as AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initFrameworks()
    }

    private fun initFrameworks() {
        applicationInjector().frameworks().forEach { it.plug(this) }
    }
}


