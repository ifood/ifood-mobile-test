package br.com.fornaro.tweetssentiment.model

data class SentimentRequest(
        val document: Document,
        val encodingType: String = "UTF8"
)

data class Document(
        val content: String,
        val type: String = "PLAIN_TEXT"
)