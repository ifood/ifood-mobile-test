package com.drury.twittermoodanalyzer.api.twitter

import com.drury.twittermoodanalyzer.api.TweetResult
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterService {

    // https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline.html
    @GET("1.1/statuses/user_timeline.json")
    fun getTweetsByUsername(@Query("screen_name") username: String): Observable<List<Tweet>>
}
