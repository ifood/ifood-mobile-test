package com.spider.twitteranalyzer.base.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Tweet constructor(
        val createdAt: String,
        val text: String,
        val user: String
) : Parcelable {

    companion object {
        const val EXTRA_TWEET = "extra_tweets"
    }
}
