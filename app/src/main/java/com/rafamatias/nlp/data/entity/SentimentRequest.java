package com.rafamatias.nlp.data.entity;

public class SentimentRequest {
    private Document document;
    private final String encodingType = "UTF8";

    private SentimentRequest(Document document) {
        this.document = document;
    }

    static class Document {
        public Document(String content) {
            this.content = content;
        }

        private String type = "PLAIN_TEXT";
        private String content;
    }

    public static SentimentRequest withContext(String content){
        return new SentimentRequest(new Document(content));

    }
}
