package com.drss.ifoodmobiletest.data

import java.util.*

data class SentimentResponse(val documentSentiment: Sentiment, val language: String, val sentences: List<Objects>)