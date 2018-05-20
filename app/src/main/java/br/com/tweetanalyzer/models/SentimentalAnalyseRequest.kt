package br.com.tweetanalyzer.models

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
data class SentimentalAnalyseRequest(val document: Document, val encodingType: String = "UTF8")