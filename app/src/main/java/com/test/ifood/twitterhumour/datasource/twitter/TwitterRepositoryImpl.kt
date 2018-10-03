package com.test.ifood.twitterhumour.datasource.twitter

import com.test.ifood.twitterhumour.model.Tweet
import com.test.ifood.twitterhumour.network.api.TwitterApi
import io.reactivex.Flowable
import javax.inject.Inject

class TwitterRepositoryImpl @Inject constructor(private val twitterApi: TwitterApi): TwitterRepository {

    override fun retrieveTweets(username: String): Flowable<List<Tweet>> {
        return twitterApi.fetchTweets(username)
    }
}