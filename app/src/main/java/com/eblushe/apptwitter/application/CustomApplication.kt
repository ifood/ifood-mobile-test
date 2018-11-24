package com.eblushe.apptwitter.application

import android.app.Application
import android.preference.PreferenceManager
import com.eblushe.apptwitter.common.di.diModule
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.RouterProvider
import com.eblushe.apptwitter.common.providers.StorageProvider
import org.koin.android.ext.android.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        RouterProvider.init(this)
        StorageProvider.init(this, preferences)
        ApiProvider.initTwitterClient(CONFIG_API_URL)

        startKoin(this, diModule)
    }
}