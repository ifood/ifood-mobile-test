package br.com.fornaro.tweetssentiment.api

import br.com.fornaro.tweetssentiment.model.TweetResponse
import br.com.fornaro.tweetssentiment.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TweeterApi {

    @GET("/1.1/users/show.json")
    fun getUser(@Query("screen_name") screenName: String): Call<User>

    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(@Query("screen_name") screenName: String): Call<List<TweetResponse>>
}