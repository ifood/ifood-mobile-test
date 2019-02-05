package com.drss.ifoodmobiletest.data

import java.util.*

data class SentimentResponse(val documentSentiment: Sentiment, val langauge: String, val sentences: List<Objects>)