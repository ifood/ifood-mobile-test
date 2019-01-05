package com.drury.twittermoodanalyzer.api.twitter

import com.drury.twittermoodanalyzer.api.TweetResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable

interface TwitterService {

    @GET("1.1/search/tweets.json")
    fun getTweetsFromUserAccount(@Query("q") username: String): Observable<TweetResult>
}
