package com.andre.test.features

import android.app.Application
import com.andre.test.core.di.AppComponent
import com.andre.test.core.di.ApplicationModule
import com.andre.test.core.di.DaggerAppComponent
import com.twitter.sdk.android.core.Twitter

class TestApplication : Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(this)
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}