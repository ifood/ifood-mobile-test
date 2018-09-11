package com.rlino.ifoodtwitterchallenge.data.twitter

import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.data.NetworkErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler
import com.rlino.ifoodtwitterchallenge.ui.Event
import retrofit2.HttpException
import javax.inject.Inject

class TweetsFetchErrorHandler @Inject constructor(
        private val networkErrorHandler: NetworkErrorHandler
) : ErrorHandler by networkErrorHandler {

    override fun handle(t: Throwable): Event<Int> {
        return when(t) {
            is HttpException -> {
                when(t.code()) {
                    404 -> Event(R.string.user_not_found)
                    401 -> Event(R.string.user_has_private_timeline)
                    else -> networkErrorHandler.handle(t)
                }
            }
            else -> networkErrorHandler.handle(t)
        }
    }
}