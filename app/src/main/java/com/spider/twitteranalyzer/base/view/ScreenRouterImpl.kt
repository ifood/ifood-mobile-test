package com.spider.twitteranalyzer.base.view

import android.content.Context
import android.content.Intent
import com.spider.twitteranalyzer.feature.detail.view.TweetDetailActivity

class ScreenRouterImpl : ScreenRouter {

    override fun getScreenIntent(context: Context, screen: ScreenRouter.Screen): Intent? {
        val c: Class<*>? = when (screen) {
            ScreenRouter.Screen.Detail -> TweetDetailActivity::class.java
            ScreenRouter.Screen.List -> null // TODO
        }
        return if (c == null) null else Intent(context, c)
    }
}
