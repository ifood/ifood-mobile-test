package com.example.tweetanalyzer.api.twitter

import com.example.tweetanalyzer.model.TweetResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    @GET("/1.1/search/tweets.json")
    fun getTweetsByUserName(@Query("q") userName: String): Call<TweetResponse>

}