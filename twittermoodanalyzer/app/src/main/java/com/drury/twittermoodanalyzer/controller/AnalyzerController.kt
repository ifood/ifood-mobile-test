package com.drury.twittermoodanalyzer.controller

import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.Interfaces
import com.drury.twittermoodanalyzer.api.ConnectionManager
import com.drury.twittermoodanalyzer.api.DocumentSentiment
import com.drury.twittermoodanalyzer.api.SentimentResult
import com.drury.twittermoodanalyzer.model.TweetModel
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable
import okhttp3.RequestBody

class AnalyzerController(val application: BaseApp): Interfaces.Controller {

    lateinit var connectionManager: ConnectionManager

    override fun getTweetsByUsername(username: String): Observable<List<TweetModel>> {
        connectionManager = ConnectionManager(application)
        return connectionManager.getTweetsByUserName(username)
                .flatMap { tweetResult -> Observable.just(transformTweetResult(tweetResult.statuses)) }
    }

    override fun analyzeTweetMood(sentence: String): Observable<DocumentSentiment> {
        connectionManager = ConnectionManager(application)
        return connectionManager.sendSentencesToCloudLanguageNatural(sentence)
                .flatMap { sentimentResult -> Observable.just(sentimentResult.documentSentiment) }
    }

    internal fun transformTweetResult(tweetResult: List<Tweet>): List<TweetModel> {
        var listTweetModel = ArrayList<TweetModel>()
        tweetResult.map {
            tweet -> listTweetModel.add(TweetModel(created = tweet.createdAt, text = tweet.text))
        }
        return listTweetModel
    }
}