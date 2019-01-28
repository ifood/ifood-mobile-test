package com.andre.test.features

import android.app.Application
import android.content.Context
import com.twitter.sdk.android.core.Twitter

class TestApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(this)
        context = this
    }
}