package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler
import javax.inject.Inject

class TweetsFetchErrorHandler @Inject constructor(
        private val networkErrorHandler: NetworkErrorHandler
) : ErrorHandler by networkErrorHandler