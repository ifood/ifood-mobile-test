package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.model.AnnotateTextRequest
import com.google.api.services.language.v1.model.AnnotateTextResponse
import com.google.api.services.language.v1.model.Document
import com.google.api.services.language.v1.model.Features

class SentimentDataSource private constructor(
        private val naturalLanguageService : CloudNaturalLanguage = NaturalLanguageServiceProvider.naturalLanguageService
) {

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: SentimentDataSource? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: SentimentDataSource().also { instance = it }
                }
    }

    fun analyzeText(text: String): AnnotateTextResponse {
        return getAnnotateTextRequest(getFeatures(), getDocument(text)).run {
            naturalLanguageService.documents().annotateText(this).execute()
        } ?: throw NoSentimentFound()
    }

    private fun getDocument(text: String) = Document().apply {
        type = "PLAIN_TEXT"
        language = "en-US"
        content = text
    }

    private fun getFeatures() = Features().apply {
        extractDocumentSentiment = true
    }

    private fun getAnnotateTextRequest(features: Features, document: Document) = AnnotateTextRequest().apply {
        this.features = features
        this.document = document
    }
}

class NoSentimentFound : Exception()