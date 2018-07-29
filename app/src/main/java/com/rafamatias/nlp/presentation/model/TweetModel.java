package com.rafamatias.nlp.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TweetModel implements Parcelable {
    private SimpleDateFormat dateParse;
    private String text;
    private String createdAt;

    /**
     * @param text
     * @param createdAt Thu Apr 06 15:24:15 +0000 2017
     */
    public TweetModel(String text, String createdAt) {
        this.text = text;
        this.createdAt = createdAt;
        this.dateParse = new SimpleDateFormat("E MMM d k:m:s Z yyyy", Locale.getDefault());
    }

    private TweetModel(Parcel in) {
        text = in.readString();
        createdAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(createdAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TweetModel> CREATOR = new Creator<TweetModel>() {
        @Override
        public TweetModel createFromParcel(Parcel in) {
            return new TweetModel(in);
        }

        @Override
        public TweetModel[] newArray(int size) {
            return new TweetModel[size];
        }
    };

    public String getText() {
        return text;
    }

    public Date createdAt() {
        try {
            return dateParse.parse(createdAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
