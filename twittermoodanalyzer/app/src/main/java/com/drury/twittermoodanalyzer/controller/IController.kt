package com.drury.twittermoodanalyzer.controller

import com.drury.twittermoodanalyzer.api.DocumentSentiment
import com.drury.twittermoodanalyzer.model.TweetModel
import io.reactivex.Observable

interface IController {

    interface AnalyzerController {
        fun getTweetsByUsername(username: String): Observable<List<TweetModel>>
        fun analyzeTweetMood(sentence: String): Observable<DocumentSentiment>
    }
}