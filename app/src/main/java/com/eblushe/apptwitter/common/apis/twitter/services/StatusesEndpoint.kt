package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.apis.twitter.responses.TweetResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StatusesEndpoint {

    @GET("1.1/statuses/user_timeline.json")
    fun getUserTimeLine(@Query("screen_name") screenName: String): Single<List<TweetResponse>>
}