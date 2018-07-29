package com.rafamatias.nlp;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;

public class AndroidApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTwitter();
    }

    private void setupTwitter(){
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .debug(true)
                .build();

        Twitter.initialize(config);

    }
}
