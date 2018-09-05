package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.model.Tweets
import io.reactivex.Single

class TwitterRepository(
        private val twitterApi: TwitterApi = MockTwitterApi()
) {

    fun getTweetsFromUser(username: String): Single<Tweets> {
        return twitterApi.getTweetsFromUser(username)
                .map { Tweets(it) }
    }

}