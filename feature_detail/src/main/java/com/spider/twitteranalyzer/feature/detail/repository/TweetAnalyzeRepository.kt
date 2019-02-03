package com.spider.twitteranalyzer.feature.detail.repository

import com.spider.twitteranalyzer.base.domain.UnknownError
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.base.extension.onNetworkErrorResumeNext
import com.spider.twitteranalyzer.base.network.api.google.GoogleApi
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentPayload
import com.spider.twitteranalyzer.base.network.api.google.dto.SentimentResponse
import io.reactivex.Single
import javax.inject.Inject


interface TweetAnalyzeRepository {

    fun analyzeTweet(tweet: Tweet): Single<SentimentResponse>

    class Impl @Inject constructor(val api: GoogleApi) : TweetAnalyzeRepository {
        override fun analyzeTweet(tweet: Tweet): Single<SentimentResponse> =
            SentimentPayload(document = SentimentPayload.DocumentPayload(text = tweet.text))
                .let { sentimentPayload ->
                    api.moodAnalyze(sentimentPayload)
                        .onNetworkErrorResumeNext()
                        .onErrorResumeNext {
                            when (it) {
                                is retrofit2.HttpException -> Single.error(UnknownError(it))
                                else -> Single.error(it)
                            }
                        }
                }

    }

}