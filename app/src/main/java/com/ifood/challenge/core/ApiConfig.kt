package com.ifood.challenge.core

import com.ifood.challenge.BuildConfig

interface ApiConfig {

    val baseUrl: String
    val networkTimeout: Long

    class Google : ApiConfig {
        override val baseUrl: String get() = BuildConfig.GOOGLE_API_URL
        override val networkTimeout: Long get() = 30L
    }

    class Twitter : ApiConfig {
        override val baseUrl: String get() = BuildConfig.TWITTER_API_URL
        override val networkTimeout: Long get() = 30L

        val consumerKey = BuildConfig.TWITTER_CONSUMER_KEY
        val consumerSecret = BuildConfig.TWITTER_CONSUMER_SECRET
        val accessToken = BuildConfig.TWITTER_ACCESS_TOKEN
        val tokenSecret = BuildConfig.TWITTER_TOKEN_SECRET
    }
}
