package com.study.vipoliveira.tweetanalyst.ui.viewentity

import com.study.vipoliveira.tweetanalyst.model.TweetResponse


class TweetsResponse private constructor(val status: Status,
                                         val data: MutableList<TweetResponse>?,
                                         val error: Throwable?){
    companion object {
        fun loading(): TweetsResponse {
            return TweetsResponse(Status.LOADING, null, null)
        }

        fun success(data: MutableList<TweetResponse>): TweetsResponse {
            return TweetsResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): TweetsResponse {
            return TweetsResponse(Status.ERROR, null, error)
        }
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}