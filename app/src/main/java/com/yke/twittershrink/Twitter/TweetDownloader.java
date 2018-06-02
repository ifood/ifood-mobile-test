package com.yke.twittershrink.Twitter;

import android.net.Uri;

import com.google.gson.reflect.TypeToken;
import com.yke.twittershrink.Const.AppConstants;
import com.yke.twittershrink.Downloader;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class TweetDownloader extends Downloader<Tweet[]> {

    /**
     *  Username to find:
     */
    private String userName;

    private RequestListener<Tweet[]> listener;

    @Override
    protected RequestListener<Tweet[]> getRequestListener() {
        return new TweetsCallback();
    }

    @Override
    protected String getUrl() {
        Uri uri = Uri.parse(AppConstants.USER_TIMELINE_URL)
                .buildUpon()
                .appendQueryParameter("screen_name", userName)
                .build();

        return uri.toString();
    }

    @Override
    protected OkHttpClient getRequestClient() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(AppConstants.CONSUMER_KEY,
                AppConstants.CONSUMER_SECRET);
        consumer.setTokenWithSecret(AppConstants.CUSTOMER_TOKEN,
                AppConstants.CUSTOMER_TOKEN_SECRET);

        return new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<Tweet[]>() {
        }.getType();
    }

    public void getTweets(String userName, RequestListener<Tweet[]> listener) {
        this.userName = userName;
        this.listener = listener;
        new GetData().execute();
    }

    /**
     *  Callback:
     */
    public class TweetsCallback implements Downloader.RequestListener<Tweet[]> {

        @Override
        public void onFinishDownload(Tweet[] tweets) {
            listener.onFinishDownload(tweets);
        }
    }
}
