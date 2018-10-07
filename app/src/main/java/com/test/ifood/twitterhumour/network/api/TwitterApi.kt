package com.test.ifood.twitterhumour.network.api

import com.test.ifood.twitterhumour.model.Tweet
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface TwitterApi {

    @GET("/1.1/statuses/user_timeline.json")
    fun fetchTweets(@Query("screen_name") username: String): Flowable<List<Tweet>>

}