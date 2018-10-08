package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.domain.repositories.TwitterRepository
import com.study.vipoliveira.tweetanalyst.model.TweetResponse
import io.reactivex.Single

class TwitterUseCasesImp
constructor(private val twitterRepository: TwitterRepository): TwitterUseCases {

    override fun getTwitter(userName: String): Single<MutableList<TweetResponse>> {
        return twitterRepository.loadTweets(userName)
    }
}