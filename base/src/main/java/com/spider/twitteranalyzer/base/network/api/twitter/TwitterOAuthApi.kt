package com.spider.twitteranalyzer.base.network.api.twitter

import com.spider.twitteranalyzer.base.network.http.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface TwitterOAuthApi {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun refreshTwitterToken(
            @Field("grant_type") grantType: String = "client_credentials"
    ): Call<TokenResponse>

}
