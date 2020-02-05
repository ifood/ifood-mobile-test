package com.ifood.challenge.home.data

import com.ifood.challenge.base.data.BaseMapper
import com.ifood.challenge.base.extensions.fromTwitterDateToUsDate
import com.ifood.challenge.home.model.Tweet
import timber.log.Timber
import javax.inject.Inject

class TweetMapper @Inject constructor() : BaseMapper<List<TweetRaw>, List<Tweet>>() {
    override val trackException: (Throwable) -> Unit
        get() = { error ->
            Timber.e(error, "Track error on Crashlytics")
        }

    override fun assertEssentialParams(raw: List<TweetRaw>) {
        raw.forEach { tweetRaw ->
            if (tweetRaw.createdAt.isNullOrBlank()) addMissingParam("tweetCreatedAt")
            if (tweetRaw.text.isNullOrBlank()) addMissingParam("tweetText")
        }
    }

    override fun copyValues(raw: List<TweetRaw>): List<Tweet> =
        raw.map { tweetRaw ->
            Tweet(
                createdAt = tweetRaw.createdAt!!.fromTwitterDateToUsDate(),
                text = tweetRaw.text!!
            )
        }
}
