package com.yke.twittershrink.Mood;

import com.yke.twittershrink.Downloader;

public class MoodImplementation {

    private final MoodViewInterface viewInterface;

    MoodImplementation (MoodViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public void onCreate(String text) {
        new CheckMood().analyzeText(text, new TextMoodAnalyzeListener());
    }

    /**
     *  Text Mood Analyzer:
     */
    private class TextMoodAnalyzeListener implements Downloader.RequestListener<Mood> {
        @Override
        public void onDownloadFinish(Mood mood) {
            if (mood != null) {
               MoodTest moodTest = new MoodTest();
               String feeling = moodTest.AnalyzeTweetMood(mood.getDocument().getScore());

               switch (feeling) {
                   case "HAPPY":
                       viewInterface.happyTweet();
                       break;

                   case "SAD":
                       viewInterface.sadTweet();
                       break;

                   case "NEUTRAL":
                       viewInterface.neutralTweet();
                       break;

                   default:
                       viewInterface.errorScreen();
                       break;
               }

               viewInterface.removeLoading();
            }
        }
    }
}
