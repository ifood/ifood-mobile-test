package com.rafamatias.nlp.presentation.model;

public class SentimentModel {
    public enum Type {
        HAPPY,
        NEUTRAL,
        SAD
    }

    private double score;

    public SentimentModel(double score) {
        this.score = score;
    }

    public Type getSentiment(){
        if(score < -0.5f){
            return Type.SAD;
        }else if(score > 0.2){
            return Type.HAPPY;
        }else {
            return Type.NEUTRAL;
        }
    }
}
