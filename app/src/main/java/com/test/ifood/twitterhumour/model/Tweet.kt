package com.test.ifood.twitterhumour.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tweet (val text: String) : Parcelable