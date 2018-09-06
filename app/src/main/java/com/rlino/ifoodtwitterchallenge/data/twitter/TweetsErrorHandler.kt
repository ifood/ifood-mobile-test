package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler

class TweetsFetchErrorHandler(
        private val networkErrorHandler: NetworkErrorHandler = NetworkErrorHandler()
) : ErrorHandler by networkErrorHandler