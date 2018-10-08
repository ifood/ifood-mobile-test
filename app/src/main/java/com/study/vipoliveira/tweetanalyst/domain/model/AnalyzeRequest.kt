package com.study.vipoliveira.tweetanalyst.domain.model

data class AnalyzeRequest(
        val document: Document,
        val encodingType: String
)