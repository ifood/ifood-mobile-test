package com.yke.twittershrink.Mood;

/**
 *  Feeling request information.
 */
public class Request {

    private MoodContent document;

    private String encodingType;

    public String getEncodingType() {
        return encodingType;
    }

    public MoodContent getDocument() {
        return document;
    }

    public void setDocument(MoodContent document) {
        this.document = document;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }
}
