package com.spider.twitteranalyzer.feature.detail.domain

import com.spider.twitteranalyzer.base.domain.BaseUseCase
import com.spider.twitteranalyzer.base.domain.ExecutorThread
import com.spider.twitteranalyzer.base.domain.PostExecutionThread
import com.spider.twitteranalyzer.base.domain.UseCase
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.detail.domain.model.Sentiment
import com.spider.twitteranalyzer.feature.detail.repository.TweetAnalyzeRepository
import javax.inject.Inject

interface AnalyzeTweetUseCase : UseCase<AnalyzeTweetUseCase.Result> {

    sealed class Result {
        data class OnSuccess(val sentiment: Sentiment) : Result()
        data class OnError(val throwable: Throwable) : Result()
    }

    fun execute(tweet: Tweet)

    class Impl @Inject constructor(
        private val repository: TweetAnalyzeRepository,
        private val mapper: Mapper,
        private val executorThread: ExecutorThread,
        private val postExecutionThread: PostExecutionThread
    ) : BaseUseCase<Result>(), AnalyzeTweetUseCase {

        override fun execute(tweet: Tweet) {
            repository.analyzeTweet(tweet)
                .map(mapper::map)
                .subscribeOn(executorThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(::success, ::error)
                .track()
        }

        private fun success(sentiment: Sentiment) {
            liveData.value = Result.OnSuccess(sentiment)
        }

        private fun error(throwable: Throwable) {
            liveData.value = Result.OnError(throwable)
        }
    }
}