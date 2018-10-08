package com.study.vipoliveira.tweetanalyst.domain

import com.study.vipoliveira.tweetanalyst.domain.repositories.GoogleRepository
import com.study.vipoliveira.tweetanalyst.model.AnalyzeResponse
import io.reactivex.Single

class GoogleUseCasesImp
constructor(private val googleRepository: GoogleRepository): GoogleUseCases {

    override fun analyzeTweet(tweet: String): Single<AnalyzeResponse> {
        return googleRepository.analyzeText(tweet)
    }
}