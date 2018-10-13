package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.domain.model.TweetResponse
import io.reactivex.Single

interface TwitterUseCases {
    fun getTwitter(userName: String): Single<MutableList<TweetResponse>>
}