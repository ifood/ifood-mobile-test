package com.test.ifood.twitterhumour.languageprocessing

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*
import com.test.ifood.twitterhumour.BuildConfig
import jersey.repackaged.jsr166e.CompletableFuture

class LanguageProcessor(text: String) {

    private val parameters: AnalyzeOptions
    private val analyzer = NaturalLanguageUnderstanding(
            "2017-02-27",
            BuildConfig.IBM_NATURAL_LANGUAGE_USERNAME,
            BuildConfig.IBM_NATURAL_LANGUAGE_PASSWORD
    )

    init {
        val sentimentOptions = SentimentOptions.Builder()
                .document(true)
                .build()
        val entities = EntitiesOptions.Builder()
                .sentiment(true)
                .build()
        val features = Features.Builder()
                .entities(entities)
                .sentiment(sentimentOptions)
                .build()

        parameters = AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build()
    }

    fun process(): CompletableFuture<AnalysisResults>? {
        return analyzer.analyze(parameters).rx()
    }
}