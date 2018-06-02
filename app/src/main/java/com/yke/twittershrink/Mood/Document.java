package com.yke.twittershrink.Mood;

import com.google.gson.annotations.SerializedName;

/**
 * Sentiment Information:
 */
public class Document {

    @SerializedName("score")
    private float score;

    public Document(float score) {
        this.score = score;
    }

    public float getScore() {
        return score;
    }
}
