package com.yke.twittershrink;

import android.content.Intent;
import android.view.View;

import com.yke.twittershrink.Mood.MoodActivity;

public class TweetViewImplementation implements TweetViewInterface {

    @Override
    public void onClick(View view, String text) {
        Intent intent = new Intent(view.getContext(), MoodActivity.class);
        intent.putExtra("Text", text);
        view.getContext().startActivity(intent);
    }
}
