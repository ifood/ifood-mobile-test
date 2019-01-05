package com.drury.twittermoodanalyzer.api

import com.twitter.sdk.android.core.models.Tweet
import okhttp3.ResponseBody
import retrofit2.Response
import rx.Observable
import java.util.*

interface IConnectionManager {
    fun hasInternetConnection(): Boolean

    fun getTweetsFromUserName(user: String): Observable<TweetResult>

}