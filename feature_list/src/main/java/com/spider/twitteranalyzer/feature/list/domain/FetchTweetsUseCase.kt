package com.spider.twitteranalyzer.feature.list.domain

import com.spider.twitteranalyzer.base.domain.BaseUseCase
import com.spider.twitteranalyzer.base.domain.ExecutorThread
import com.spider.twitteranalyzer.base.domain.PostExecutionThread
import com.spider.twitteranalyzer.base.domain.UseCase
import com.spider.twitteranalyzer.base.domain.model.Tweet
import com.spider.twitteranalyzer.feature.list.repository.TweetsRepository
import timber.log.Timber
import javax.inject.Inject

interface FetchTweetsUseCase : UseCase<FetchTweetsUseCase.Result> {

    sealed class Result {
        data class OnSuccess(val tweets: List<Tweet>) : Result()
        data class OnError(val throwable: Throwable) : Result()
    }

    fun execute(screenName: String)

    class Impl @Inject constructor(
        private val repository: TweetsRepository,
        private val mapper: Mapper,
        private val executorThread: ExecutorThread,
        private val postExecutionThread: PostExecutionThread
    ) : FetchTweetsUseCase, BaseUseCase<Result>() {

        override fun execute(screenName: String) {
            repository.fetchTweets(screenName)
                .map(mapper::map)
                .subscribeOn(executorThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(::success, ::error)
                .track()
        }

        private fun success(tweets: List<Tweet>) {
            liveData.value = Result.OnSuccess(tweets)
        }

        private fun error(throwable: Throwable) {
            Timber.e(throwable)
            liveData.value = Result.OnError(throwable)
        }
    }
}