package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.model.Tweet
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {


    @GET("statuses/user_timeline.json")
    fun getTweetsFromUser(@Query("screen_name") from: String,
                          @Query("exclude_replies") excludeReplies: Boolean = true,
                          @Query("include_rts") includeRts: Boolean = false
    ): Single<List<Tweet>>

}