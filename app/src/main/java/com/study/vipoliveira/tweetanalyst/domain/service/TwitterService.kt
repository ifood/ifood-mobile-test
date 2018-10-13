package com.study.vipoliveira.tweetanalyst.domain.service

import com.study.vipoliveira.tweetanalyst.domain.model.TokenResponse
import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse
import io.reactivex.Single
import retrofit2.http.*

interface TwitterService{
    @GET("/1.1/statuses/user_timeline.json")
    fun getTweets(@Query("screen_name") screenName: String,
                  @Query("include_rts") includeRts: Boolean = false,
                  @Query("count") count: Int = 50
    ): Single<MutableList<TweetResponse>>


    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(@Header("Authorization") authorization: String,
                 @Field("grant_type") grantType: String = "client_credentials"
    ): Single<TokenResponse>
}