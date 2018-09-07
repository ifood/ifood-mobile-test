package com.rlino.ifoodtwitterchallenge.data.google

import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler
import com.rlino.ifoodtwitterchallenge.ui.Event

class SentimentErrorHandler(
        private val networkErrorHandler: NetworkErrorHandler = NetworkErrorHandler()
) : ErrorHandler by networkErrorHandler {

    override fun handle(t: Throwable): Event<Int> {
        return when(t) {
            is NoSentimentFound -> Event(R.string.no_sentiment_found)
            else -> networkErrorHandler.handle(t)
        }
    }
}