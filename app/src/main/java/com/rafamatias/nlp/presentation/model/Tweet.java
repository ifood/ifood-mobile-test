package com.rafamatias.nlp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tweet implements Parcelable {
    private String text;

    public Tweet(String text) {
        this.text = text;
    }

    private Tweet(Parcel in) {
        text = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public String getText() {
        return text;
    }


}
