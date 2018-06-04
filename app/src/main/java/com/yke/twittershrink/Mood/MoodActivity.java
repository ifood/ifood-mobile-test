package com.yke.twittershrink.Mood;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yke.twittershrink.R;

public class MoodActivity extends AppCompatActivity implements MoodViewInterface {

    public String mMoodText;

    private MoodImplementation moodImp = new MoodImplementation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_activity);

        Intent intent = getIntent();
        mMoodText = intent.getStringExtra("Text");
        moodImp.onCreate(mMoodText);
    }

    @Override
    public void sadTweet() {
        TextView textView = findViewById(R.id.moodText);
        ImageView imageView = findViewById(R.id.emojiView);
        ConstraintLayout layout = findViewById(R.id.moodLayout);

        //Set Values:
        textView.setText(R.string.sad_tweet);
        imageView.setImageResource(R.drawable.sad_emoji);
        layout.setBackgroundColor(getResources().getColor(R.color.SadBlue));
    }

    @Override
    public void happyTweet() {
        TextView textView = findViewById(R.id.moodText);
        ImageView imageView = findViewById(R.id.emojiView);
        ConstraintLayout layout = findViewById(R.id.moodLayout);

        //Set Values:
        textView.setText(R.string.happy_tweet);
        imageView.setImageResource(R.drawable.happy_emoji);
        layout.setBackgroundColor(getResources().getColor(R.color.VibrantYellow));
    }

    @Override
    public void neutralTweet() {
        TextView textView = findViewById(R.id.moodText);
        ImageView imageView = findViewById(R.id.emojiView);
        ConstraintLayout layout = findViewById(R.id.moodLayout);

        //Set Values:
        textView.setText(R.string.neutral_tweet);
        imageView.setImageResource(R.drawable.neutral_emoji);
        layout.setBackgroundColor(getResources().getColor(R.color.NeutralGrey));
    }

    @Override
    public void errorScreen() {
        TextView textView = findViewById(R.id.moodText);
        ImageView imageView = findViewById(R.id.emojiView);
        textView.setText(R.string.moodError);
        imageView.setImageResource(R.drawable.monocular_emoji);
    }

    @Override
    public void removeLoading() {
        ProgressBar moodProgress = findViewById(R.id.moodProgressBar);
        moodProgress.setVisibility(View.GONE);
    }
}
