package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.apis.twitter.responses.TwitterOAuthToken
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface OAuthEndpoint {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun requestToken(@Field("grant_type") grantType: String): Single<TwitterOAuthToken>
}