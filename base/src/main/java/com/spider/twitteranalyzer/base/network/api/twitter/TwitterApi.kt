package com.spider.twitteranalyzer.base.network.api.twitter

import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface TwitterApi {

    @GET("1.1/statuses/user_timeline.json")
    fun fetchTweets(@Query("screen_name") userName: String): Single<List<TweetResponse>>
}

