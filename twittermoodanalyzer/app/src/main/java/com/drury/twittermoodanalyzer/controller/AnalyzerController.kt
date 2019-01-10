package com.drury.twittermoodanalyzer.controller

import android.content.Context
import com.drury.twittermoodanalyzer.api.ConnectionManager
import com.drury.twittermoodanalyzer.api.DocumentSentiment
import com.drury.twittermoodanalyzer.model.TweetModel
import com.twitter.sdk.android.core.models.Tweet
import io.reactivex.Observable

class AnalyzerController(val context: Context): IController.AnalyzerController {

    lateinit var connectionManager: ConnectionManager

    override fun getTweetsByUsername(username: String): Observable<List<TweetModel>> {
        connectionManager = ConnectionManager(context)
        return connectionManager.getTweetsByUserName(username)
                .flatMap { tweetResult -> Observable.just(transformTweetResult(tweetResult)) }
    }

    override fun analyzeTweetMood(sentence: String): Observable<DocumentSentiment> {
        connectionManager = ConnectionManager(context)
        return connectionManager.sendSentencesToCloudLanguageNatural(sentence)
                .flatMap { sentimentResult -> Observable.just(sentimentResult.documentSentiment) }
    }

    private fun transformTweetResult(tweetResult: List<Tweet>): List<TweetModel> {
        val listTweetModel = ArrayList<TweetModel>()
        tweetResult.map {
            tweet -> listTweetModel.add(TweetModel(created = tweet.createdAt, text = tweet.text))
        }
        return listTweetModel
    }
}