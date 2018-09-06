package com.rlino.ifoodtwitterchallenge

import android.app.Application
import timber.log.Timber
import com.crashlytics.android.Crashlytics
import com.rlino.ifoodtwitterchallenge.logger.CrashlyticsTree
import io.fabric.sdk.android.Fabric



class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())

        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(CrashlyticsTree())

    }
}