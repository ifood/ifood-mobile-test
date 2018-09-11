package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.BuildConfig
import com.rlino.ifoodtwitterchallenge.model.Tweet
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface TwitterApi {

    @GET("${BuildConfig.TWITTER_API_VERSION}/statuses/user_timeline.json")
    fun getTweetsFromUser(@Query("screen_name") from: String,
                          @Query("exclude_replies") excludeReplies: Boolean = true,
                          @Query("include_rts") includeRts: Boolean = false,
                          @Query("count") count: Int = 50
    ): Single<List<Tweet>>

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(
            @Header("Authorization") authorization: String,
            @Field("grant_type") grantType: String = "client_credentials"
    ): Single<TokenResponse>

}