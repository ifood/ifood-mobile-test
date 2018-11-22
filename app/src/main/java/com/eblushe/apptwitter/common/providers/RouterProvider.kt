package com.eblushe.apptwitter.common.providers

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eblushe.apptwitter.features.home.views.HomeActivity

@SuppressLint("StaticFieldLeak")
object RouterProvider {
    var applicationContext: Context? = null
    var runningUITest: Boolean = false

    fun init(application: Application) {
        applicationContext = application.applicationContext
    }

    fun openHomeScreen(bundle: Bundle? = null, finish: Boolean = false) {
        if (runningUITest) return

        val intent = Intent(applicationContext, HomeActivity::class.java)
        bundle?.let {
            intent.putExtras(it)
        }

        if (finish) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        applicationContext?.apply {
            startActivity(intent)
        }
    }
}