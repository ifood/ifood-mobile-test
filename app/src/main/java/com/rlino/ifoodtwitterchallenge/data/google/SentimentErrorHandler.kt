package com.rlino.ifoodtwitterchallenge.data.google

import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler

class SentimentErrorHandler(
        private val networkErrorHandler: NetworkErrorHandler = NetworkErrorHandler()
) : ErrorHandler by networkErrorHandler