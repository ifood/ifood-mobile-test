package com.example.tweetanalyzer.api.twitter

import com.example.tweetanalyzer.model.TokenResponse
import com.example.tweetanalyzer.model.Tweet
import retrofit2.Call
import retrofit2.http.*

interface TwitterService {

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(@Field("grant_type") grantType: String = "client_credentials"): Call<TokenResponse>

    @GET("/1.1/statuses/user_timeline.json")
    fun getTweetsByUserName(@Query("screen_name") userName: String, @Query("include_rts") inludeRT: Boolean = false): Call<List<Tweet>>
}