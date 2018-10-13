package com.study.vipoliveira.tweetanalyst.domain.repositories

import com.study.vipoliveira.tweetanalyst.domain.service.GoogleService
import com.study.vipoliveira.tweetanalyst.domain.model.AnalyzeRequest
import com.study.vipoliveira.tweetanalyst.domain.model.AnalyzeResponse
import com.study.vipoliveira.tweetanalyst.domain.model.Document
import io.reactivex.Single

class GoogleRepositoryImp
constructor(private val googleService: GoogleService): GoogleRepository {

    override fun analyzeText(tweet: String): Single<AnalyzeResponse> {
        val document = Document("PLAIN_TEXT", tweet)
        val analyzeRequest = AnalyzeRequest(document, "UTF8")

        return googleService
                .postAnalysis(analyzeRequest)
    }

}
