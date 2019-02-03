package com.spider.twitteranalyzer.base.network.http

import com.spider.twitteranalyzer.base.BuildConfig
import com.spider.twitteranalyzer.base.storage.Prefs
import javax.inject.Inject

interface TokenStorage {
    var token: String?
    val bearerToken: String

    class TwitterStorage @Inject constructor() : TokenStorage {

        override val bearerToken: String by Prefs(
                "twitterStorage.bearerToken",
                "${BuildConfig.TWITTER_CONSUMER_KEY}:${BuildConfig.TWITTER_CONSUMER_SECRET}"
        )

        override var token: String? by Prefs("twitterStorage.token", null)

    }
}