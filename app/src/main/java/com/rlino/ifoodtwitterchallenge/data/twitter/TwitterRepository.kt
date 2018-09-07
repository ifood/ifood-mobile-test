package com.rlino.ifoodtwitterchallenge.data.twitter

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

}