package com.rafamatias.nlp;

import android.app.Application;
import android.util.Log;

import com.rafamatias.nlp.data.entity.SentimentRequest;
import com.rafamatias.nlp.data.entity.SentimentResponse;
import com.rafamatias.nlp.data.net.GoogleApi;
import com.rafamatias.nlp.data.net.GoogleService;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;

public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTwitter();
    }

    private void setupTwitter(){
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET))
                .debug(true)
                .build();

        Twitter.initialize(config);

    }
}
