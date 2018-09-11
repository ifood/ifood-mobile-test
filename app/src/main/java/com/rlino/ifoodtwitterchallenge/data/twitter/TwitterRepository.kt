package com.rlino.ifoodtwitterchallenge.data.twitter

import android.util.Base64
import com.rlino.ifoodtwitterchallenge.BuildConfig
import com.rlino.ifoodtwitterchallenge.model.Tweets
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TwitterRepository @Inject constructor(
        private val twitterApi: TwitterApi
) {

    fun getTweetsFromUser(username: String): Single<Tweets> {
        return twitterApi.getTweetsFromUser(username)
                .map { Tweets(it) }
    }

    fun getTwitterToken(): Single<String> {
        return twitterApi.getToken(
              "Basic " + Base64.encodeToString("${BuildConfig.TWITTER_CONSUMER_KEY}:${BuildConfig.TWITTER_SECRET_KEY}".toByteArray(), Base64.NO_WRAP)
        ).map { it.accessToken }
    }

}