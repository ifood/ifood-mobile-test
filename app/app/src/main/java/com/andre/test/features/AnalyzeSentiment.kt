package com.andre.test.features

import com.andre.test.core.interactor.UseCase
import com.andre.test.core.network.NetworkResponse
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.model.AnnotateTextRequest
import com.google.api.services.language.v1.model.Document
import com.google.api.services.language.v1.model.Features
import java.io.IOException
import javax.inject.Inject

class AnalyzeSentiment
@Inject constructor(private val naturalLanguageService: CloudNaturalLanguage) :
    UseCase<NetworkResponse, AnalyzeSentiment.Params>() {

    override suspend fun run(params: Params): NetworkResponse {
        val document = Document()
        document.type = "PLAIN_TEXT"
        document.content = params.twitterContent

        val features = Features()
        features.extractDocumentSentiment = true

        val request = AnnotateTextRequest()
        request.document = document
        request.features = features

        var response: NetworkResponse
        try {
            val annotateTextResponse = naturalLanguageService.documents().annotateText(request).execute()
            response = NetworkResponse.Success(annotateTextResponse.documentSentiment)
        } catch (e: IOException) {
            response = NetworkResponse.FetchFailure()
        }

        return response
    }

    data class Params(val twitterContent: String) {

        override fun toString(): String {
            return twitterContent
        }
    }
}