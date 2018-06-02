package com.yke.twittershrink.Mood;

public class MoodTest {

    public String AnalyzeTweetMood(float score) {
        String mood = null;

        if (score <= 1.0 && score >= 0.25) {
            mood = "HAPPY";
        } else if (score < 0.25 && score > -0.25) {
            mood = "NEUTRAL";
        } else if (score <= -0.25 && score >= -1.0) {
            mood = "SAD";
        }

        return mood;
    }
}
