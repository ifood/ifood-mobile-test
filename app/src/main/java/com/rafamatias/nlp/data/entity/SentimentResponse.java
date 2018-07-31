package com.rafamatias.nlp.data.entity;

public class SentimentResponse {
    private Result documentSentiment;

    static class Result {
        private double magnitude;
        private double score;
    }
}
