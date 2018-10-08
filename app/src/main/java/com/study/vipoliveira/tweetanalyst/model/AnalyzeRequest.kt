package com.study.vipoliveira.tweetanalyst.model

data class AnalyzeRequest(
        val document: Document,
        val encodingType: String
)