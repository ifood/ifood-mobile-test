package com.study.vipoliveira.tweetanalyst.domain.repositories

import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse
import io.reactivex.Single

interface TwitterRepository {
    fun loadTweets(screenName: String): Single<MutableList<TweetResponse>>
}
