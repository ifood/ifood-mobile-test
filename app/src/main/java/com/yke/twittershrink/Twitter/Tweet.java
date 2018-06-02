package com.yke.twittershrink.Twitter;

import com.google.gson.annotations.SerializedName;

/**
 *  Tweet information:
 */
public class Tweet {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }
}
