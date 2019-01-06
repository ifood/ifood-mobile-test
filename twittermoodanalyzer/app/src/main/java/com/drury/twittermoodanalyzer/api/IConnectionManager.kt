package com.drury.twittermoodanalyzer.api

import io.reactivex.Observable


interface IConnectionManager {
    fun hasInternetConnection(): Boolean

    fun getTweetsByUserName(user: String): Observable<TweetResult>

    fun sendSentencesToCloudLanguageNatural(sentence: String): Observable<SentimentResult>
}