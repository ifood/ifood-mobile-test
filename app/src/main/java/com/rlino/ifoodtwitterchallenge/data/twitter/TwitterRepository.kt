package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.model.Tweets
import io.reactivex.Single

class TwitterRepository private constructor(
        private val twitterApi: TwitterApi = MockTwitterApi()
) {

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TwitterRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: TwitterRepository().also { instance = it }
                }
    }

    fun getTweetsFromUser(username: String): Single<Tweets> {
        return twitterApi.getTweetsFromUser(username)
                .map { Tweets(it) }
    }

}