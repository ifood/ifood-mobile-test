package com.example.tweetanalyzer.api.twitter

import com.example.tweetanalyzer.api.BaseApi
import com.example.tweetanalyzer.model.TokenResponse
import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call

class TwitterApi(twitterInterceptor: TwitterInterceptor) : BaseApi(){

    private val baseUrl = "https://api.twitter.com"

    private val api = build(baseUrl, twitterInterceptor).create(TwitterService::class.java)

    fun getToken(): Call<TokenResponse> = api.getToken()

    fun getTweetsByUserName(userName: String): Call<List<Tweet>> = api.getTweetsByUserName(userName)
}