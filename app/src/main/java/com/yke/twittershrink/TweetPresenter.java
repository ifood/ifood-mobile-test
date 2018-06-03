package com.yke.twittershrink;

import android.content.Intent;
import android.view.View;

import com.yke.twittershrink.Mood.MoodActivity;

public class TweetPresenter implements TweetViewInterface {

    @Override
    public void onTweetClicked(View view, String text) {
        Intent intent = new Intent(view.getContext(), MoodActivity.class);
        intent.putExtra("Text", text);
        view.getContext().startActivity(intent);
    }
}
