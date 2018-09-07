package com.rlino.ifoodtwitterchallenge.data.google

import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler
import javax.inject.Inject

class SentimentErrorHandler @Inject constructor(
        private val networkErrorHandler: NetworkErrorHandler
) : ErrorHandler by networkErrorHandler