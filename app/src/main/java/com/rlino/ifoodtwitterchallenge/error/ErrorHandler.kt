package com.rlino.ifoodtwitterchallenge.error

import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.ui.Event
import timber.log.Timber

interface ErrorHandler {

    fun handle(t: Throwable): Event<Int>

    fun logThenHandle(t: Throwable): Event<Int> {
        Timber.e(t)
        return handle(t)
    }

}

class CommonErrorHandler : ErrorHandler {
    override fun handle(t: Throwable): Event<Int> = Event(R.string.something_went_wrong)
}