package com.drury.twittermoodanalyzer.controller

import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.api.ConnectionManager
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable

class TweetController(val application: BaseApp) {

    lateinit var connectionManager: ConnectionManager

    fun getTweetsByUsername(username: String): Observable<List<Tweet>> {
        connectionManager = ConnectionManager(application)
        return connectionManager.getTweetsByUserName(username)
                .flatMap { tweetResult -> Observable.just(tweetResult.statuses) }

    }
}