package com.drury.twittermoodanalyzer.api

import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable


interface IConnectionManager {
    fun hasInternetConnection(): Boolean

    fun getTweetsByUserName(user: String): Observable<List<Tweet>>

    fun sendSentencesToCloudLanguageNatural(sentence: String): Observable<SentimentResult>
}