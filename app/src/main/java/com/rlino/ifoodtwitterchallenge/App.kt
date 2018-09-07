package com.rlino.ifoodtwitterchallenge

import com.crashlytics.android.Crashlytics
import com.rlino.ifoodtwitterchallenge.di.DaggerAppComponent
import com.rlino.ifoodtwitterchallenge.logger.CrashlyticsTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber


class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(CrashlyticsTree())

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}