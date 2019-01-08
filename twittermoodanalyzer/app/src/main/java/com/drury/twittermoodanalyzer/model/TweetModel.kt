package com.drury.twittermoodanalyzer.model

import android.os.Parcel
import android.os.Parcelable


class TweetModel(var text: String, var created: String): Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeString(text)
            dest.writeString(created)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

}