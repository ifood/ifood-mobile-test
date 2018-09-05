package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.model.Tweet
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit

class MockTwitterApi : TwitterApi {

    override fun getTweetsFromUser(from: String, excludeReplies: Boolean, includeRts: Boolean): Single<List<Tweet>> {
        return Single.just(listOf(
                Tweet(Date(), 6126, "tweet1"),
                Tweet(Date(), 61213, "tweet2"),
                Tweet(Date(), 6125123, "tweet3"),
                Tweet(Date(), 61267162, "tweet4"),
                Tweet(Date(), 61123643, "tweet5")
        )).delay(3, TimeUnit.SECONDS)
    }

}