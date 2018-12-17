package com.example.tweetanalyzer.api.twitter

import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    @GET("/1.1/statuses/user_timeline.json")
    fun getTweetsByUserName(@Query("screen_name") userName: String): Call<List<Tweet>>

}