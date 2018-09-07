package com.rlino.ifoodtwitterchallenge.error

import com.rlino.ifoodtwitterchallenge.R
import com.rlino.ifoodtwitterchallenge.ui.Event

interface ErrorHandler {

    fun handle(t: Throwable): Event<Int>

}

class CommonErrorHandler : ErrorHandler {
    override fun handle(t: Throwable): Event<Int> = Event(R.string.something_went_wrong)
}