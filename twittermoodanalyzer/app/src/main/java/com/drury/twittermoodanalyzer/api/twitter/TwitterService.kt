package com.drury.twittermoodanalyzer.api.twitter

import com.drury.twittermoodanalyzer.api.TweetResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    @GET("1.1/search/tweets.json")
    fun getTweetsByUsername(@Query("q") username: String): Observable<TweetResult>
}
