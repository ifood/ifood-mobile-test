package com.yke.twittershrink.Mood;

import com.google.gson.annotations.SerializedName;

public class Mood {

    /**
     *  Text sentiment information.
     */
    @SerializedName("documentSentiment")
    private Document document;

    public Mood(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
}
