package com.ifood.challenge.home.data.twitter

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    @GET("1.1/users/search.json")
    fun searchUsers(
        @Query("q") userName: String
    ): Single<List<TwitterUserRaw>>

    @GET("1.1/statuses/user_timeline.json")
    fun fetchUserTimeline(
        @Query("user_id") userId: Long
    ): Single<List<TweetRaw>>
}
