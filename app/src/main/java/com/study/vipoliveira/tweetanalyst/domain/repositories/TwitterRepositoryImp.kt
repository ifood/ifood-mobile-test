package com.study.vipoliveira.tweetanalyst.domain.repositories

import android.util.Base64
import com.study.vipoliveira.tweetanalyst.BuildConfig
import com.study.vipoliveira.tweetanalyst.model.TweetResponse
import com.study.vipoliveira.tweetanalyst.domain.service.TwitterService
import com.study.vipoliveira.tweetanalyst.store.TwitterStorePref
import io.reactivex.Single

class TwitterRepositoryImp
constructor(private val twitterService: TwitterService, private val twitterStorePref: TwitterStorePref) : TwitterRepository {
    override fun loadTweets(screenName: String): Single<MutableList<TweetResponse>> {
        return getAccessToken().flatMap { twitterService.getTweets(screenName) }
    }

    private fun getAccessToken(): Single<String> {
        if (!twitterStorePref.getToken().isEmpty()) {
            return Single.just(twitterStorePref.getToken())
        }

        return twitterService
                .getToken("Basic " + Base64.encodeToString("${BuildConfig.TWITTER_CONSUMER_KEY}:${BuildConfig.TWITTER_SECRET_KEY}".toByteArray(), Base64.NO_WRAP))
                .flatMap {
                    if (it.tokenType == "bearer") {
                        Single.just(it.accessToken)
                    } else {
                        Single.error(Throwable())
                    }
                }
                .doOnSuccess { twitterStorePref.saveToken(it) }
                .onErrorResumeNext(Single.error(Throwable()))
    }
}