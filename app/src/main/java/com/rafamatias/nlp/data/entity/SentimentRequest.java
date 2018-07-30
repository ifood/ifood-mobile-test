package com.rafamatias.nlp.data.entity;

public class SentimentRequest {
    private Document document;
    private final String encodingType = "UTF8";

    public class Document {
        private String type;
        private String content;
    }
}
