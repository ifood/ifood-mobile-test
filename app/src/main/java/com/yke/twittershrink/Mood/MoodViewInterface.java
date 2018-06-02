package com.yke.twittershrink.Mood;

interface MoodViewInterface {

    /**
     *  Mood interface:
     */

    void errorScreen();

    void removeLoading();

    /**
     *  Tweet Feelings:
     */

    void sadTweet();

    void happyTweet();

    void neutralTweet();
}
