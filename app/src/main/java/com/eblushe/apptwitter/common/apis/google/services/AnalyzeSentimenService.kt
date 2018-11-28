package com.eblushe.apptwitter.common.apis.twitter.services

import com.eblushe.apptwitter.common.apis.google.requests.DocumentContent
import com.eblushe.apptwitter.common.apis.google.requests.DocumentRequest
import com.eblushe.apptwitter.common.apis.google.responses.FeelingResponse
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.services.BaseService
import io.reactivex.Single

class AnalyzeSentimenService(apiProvider: ApiProvider) : BaseService<AnalyzeSentimentEndpoint>() {
    override val endpoint: AnalyzeSentimentEndpoint = apiProvider.googleClient.create(AnalyzeSentimentEndpoint::class.java)

    fun postFeeling(text: String): Single<FeelingResponse> {
        val documentRequest = DocumentRequest(DocumentContent(content= text))
        return endpoint.postFeeling(documentRequest)
    }
}