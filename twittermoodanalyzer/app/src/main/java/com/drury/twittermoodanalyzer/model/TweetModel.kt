package com.drury.twittermoodanalyzer.model

import android.os.Parcel
import android.os.Parcelable


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TweetModel(var text: String, var created: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeString(text)
            dest.writeString(created)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TweetModel> {
        override fun createFromParcel(parcel: Parcel): TweetModel {
            return TweetModel(parcel)
        }

        override fun newArray(size: Int): Array<TweetModel?> {
            return arrayOfNulls(size)
        }
    }

}