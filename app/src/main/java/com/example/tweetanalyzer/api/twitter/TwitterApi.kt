package com.example.tweetanalyzer.api.twitter

import com.example.tweetanalyzer.api.BaseApi
import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call

class TwitterApi : BaseApi(){

    private val baseUrl = "https://api.twitter.com"

    private val api = build(baseUrl, TwitterInterceptor()).create(TwitterService::class.java)

    fun getTweetsByUserName(userName: String): Call<List<Tweet>> = api.getTweetsByUserName(userName)
}