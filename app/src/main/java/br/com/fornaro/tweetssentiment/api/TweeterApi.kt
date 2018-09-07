package br.com.fornaro.tweetssentiment.api

import br.com.fornaro.tweetssentiment.model.Tweet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TweeterApi {

    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(@Query("screen_name") screenName: String): Call<List<Tweet>>
}