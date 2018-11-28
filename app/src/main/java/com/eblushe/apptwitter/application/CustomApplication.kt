package com.eblushe.apptwitter.application

import android.app.Application
import android.preference.PreferenceManager
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.eblushe.apptwitter.BuildConfig
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
        ApiProvider.initTwitterClient(BuildConfig.TWITTER_API_URL)
        ApiProvider.initGoogleClient(BuildConfig.GOOGLE_API_URL)

        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

        startKoin(this, diModule)
    }
}