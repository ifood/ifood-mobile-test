package com.rafamatias.nlp.data.entity;

public class SentimentResponse {
    private Result documentSentiment;

    public double getScore() {
        if(documentSentiment == null) {
            return 0.0;
        }

        return documentSentiment.score;
    }


    static class Result {
        private double score;
    }
}
