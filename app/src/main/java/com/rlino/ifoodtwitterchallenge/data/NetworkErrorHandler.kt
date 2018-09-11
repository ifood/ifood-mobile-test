package com.rlino.ifoodtwitterchallenge.data

import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.error.CommonErrorHandler
import com.rlino.ifoodtwitterchallenge.error.ErrorHandler
import com.rlino.ifoodtwitterchallenge.ui.Event
import java.io.IOException
import javax.inject.Inject

class NetworkErrorHandler @Inject constructor(
        private val commonErrorHandler: CommonErrorHandler
) : ErrorHandler by commonErrorHandler {

    override fun handle(t: Throwable): Event<Int> {
        return when(t) {
            is IOException -> Event(R.string.network_failure_try_again)
            else -> commonErrorHandler.handle(t)
        }
    }

}