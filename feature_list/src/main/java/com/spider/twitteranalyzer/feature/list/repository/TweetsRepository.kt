package com.spider.twitteranalyzer.feature.list.repository

import com.spider.twitteranalyzer.base.domain.NotFoundAccountError
import com.spider.twitteranalyzer.base.domain.PrivateAccountError
import com.spider.twitteranalyzer.base.domain.UnknownError
import com.spider.twitteranalyzer.base.extension.onNetworkErrorResumeNext
import com.spider.twitteranalyzer.base.network.api.twitter.TwitterApi
import com.spider.twitteranalyzer.base.network.api.twitter.dto.TweetResponse
import io.reactivex.Single
import javax.inject.Inject


interface TweetsRepository {
    fun fetchTweets(screenName: String): Single<List<TweetResponse>>

    class Impl @Inject constructor(private val api: TwitterApi) : TweetsRepository {
        override fun fetchTweets(screenName: String): Single<List<TweetResponse>> =
            api.fetchTweets(screenName)
                .onNetworkErrorResumeNext()
                .onDomainErrorResumeNext()


        private fun <T> Single<T>.onDomainErrorResumeNext(): Single<T> = this.onErrorResumeNext {
            when (it) {
                is retrofit2.HttpException -> when (it.code()) {
                    401 -> Single.error(PrivateAccountError())
                    404 -> Single.error(NotFoundAccountError())
                    else -> Single.error(UnknownError(it))
                }

                else -> Single.error(it)
            }
        }
    }
}
