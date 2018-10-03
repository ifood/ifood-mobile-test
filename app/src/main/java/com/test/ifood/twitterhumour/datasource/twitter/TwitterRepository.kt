package com.test.ifood.twitterhumour.datasource.twitter

import com.test.ifood.twitterhumour.model.Tweet
import io.reactivex.Flowable

interface TwitterRepository {

    fun retrieveTweets(username: String): Flowable<List<Tweet>>

}