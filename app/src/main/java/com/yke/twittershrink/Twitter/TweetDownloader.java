package com.yke.twittershrink.Twitter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yke.twittershrink.Const.TwitterApiConstants;
import com.yke.twittershrink.Downloader;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class TweetDownloader extends Downloader<Tweet[]> {

    private static final String TAG = TweetDownloader.class.getSimpleName();

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
        Uri uri = Uri.parse(TwitterApiConstants.USER_TIMELINE_URL)
                .buildUpon()
                .appendQueryParameter("screen_name", userName)
                .build();

        return uri.toString();
    }

    @Override
    protected OkHttpClient getRequestClient() {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(TwitterApiConstants.CONSUMER_KEY,
                TwitterApiConstants.CONSUMER_SECRET);
        consumer.setTokenWithSecret(TwitterApiConstants.CUSTOMER_TOKEN,
                TwitterApiConstants.CUSTOMER_TOKEN_SECRET);

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

    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<Void, Void, Tweet[]> {

        @Override
        protected Tweet[] doInBackground(Void... params) {
            Tweet[] tweets = null;

            try {
                Response response = getRequestClient().newCall(getRequest()).execute();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String json = response
                            .body()
                            .string();

                    Gson gson = new GsonBuilder()
                            .create();
                    tweets = gson.fromJson(json, getType());
                }

            } catch (IOException e) {
                Log.e(TAG, "Error on request: ", e);
            }

            return tweets;
        }

        @Override
        protected void onPostExecute(Tweet[] tweets) {
            if (getRequestListener() != null) {
                getRequestListener().onDownloadFinish(tweets);
            }
        }
    }

    /**
     *  Callback:
     */
    public class TweetsCallback implements Downloader.RequestListener<Tweet[]> {

        @Override
        public void onDownloadFinish(Tweet[] tweets) {
            listener.onDownloadFinish(tweets);
        }
    }
}
