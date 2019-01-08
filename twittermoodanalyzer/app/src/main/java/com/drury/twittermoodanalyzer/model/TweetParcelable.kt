package com.drury.twittermoodanalyzer.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class TweetParcelable : Parcelable {
    var tweetText: String? = null
    var tweetDate: String? = null
}