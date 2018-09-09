package com.rlino.ifoodtwitterchallenge.data.google

import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SentimentDataSource @Inject constructor(
        private val naturalLanguageService : CloudNaturalLanguage
) {

    fun analyzeText(text: String): Sentiment {
        return getAnnotateTextRequest(getFeatures(), getDocument(text)).run {
            naturalLanguageService.documents().annotateText(this).execute()
        }.documentSentiment
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