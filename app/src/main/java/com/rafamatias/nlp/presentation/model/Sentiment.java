package com.rafamatias.nlp.presentation.model;

public class Sentiment {
    public enum Type {
        HAPPY,
        NEUTRAL,
        SAD
    }

    private float score;
    private float magnitude;

    public Sentiment(float score, float magnitude) {
        this.score = score;
        this.magnitude = magnitude;
    }

    public Type getSentiment(){
        if(score < -0.5f){
            return Type.SAD;
        }else if(score > 0.2){
            return Type.HAPPY;
        }else{
            return Type.NEUTRAL;
        }
    }
}
