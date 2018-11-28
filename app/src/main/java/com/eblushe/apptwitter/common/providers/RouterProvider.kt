package com.eblushe.apptwitter.common.providers

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eblushe.apptwitter.features.home.views.HomeActivity
import com.eblushe.apptwitter.features.userdetails.views.UserDetailsActivity

@SuppressLint("StaticFieldLeak")
object RouterProvider {
    var applicationContext: Context? = null
    var runningUITest: Boolean = false

    fun init(application: Application) {
        applicationContext = application.applicationContext
    }

    fun openHomeScreen(activity: Activity?, bundle: Bundle? = null, finish: Boolean = false) {
        if (runningUITest) return

        val intent = Intent(applicationContext, HomeActivity::class.java)
        bundle?.let {
            intent.putExtras(it)
        }

        if (finish) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        activity?.apply {
            startActivity(intent)
        }
    }

    fun openUserDetailsScreen(activity: Activity?, screenName: String, finish: Boolean = false) {
        if (runningUITest) return

        val bundle = Bundle()
        bundle.putString(UserDetailsActivity.PARAMS_SCREEN_NAME, screenName)

        val intent = Intent(applicationContext, UserDetailsActivity::class.java)
            intent.putExtras(bundle)

        if (finish) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        activity?.apply {
            startActivity(intent)
        }
    }
}