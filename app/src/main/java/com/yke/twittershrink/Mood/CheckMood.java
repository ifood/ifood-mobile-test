package com.yke.twittershrink.Mood;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yke.twittershrink.Const.GoogleApiConstants;
import com.yke.twittershrink.Downloader;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckMood extends Downloader<Mood> {

    private static final String TAG = CheckMood.class.getSimpleName();
    private String text;

    private RequestListener<Mood> listener;

    public void analyzeText(String text, RequestListener<Mood> listener) {
        this.text = text;
        this.listener = listener;
        new GetMoodData().execute();
    }

    @Override
    protected RequestListener<Mood> getRequestListener() {
        return new CheckMoodCallback();
    }

    @Override
    protected Type getType() {
        return new TypeToken<Mood>() {
        }.getType();
    }

    @Override
    protected String getUrl() {
        Uri uri = Uri.parse(GoogleApiConstants.ANALYZE_SENTIMENT_URL)
                .buildUpon()
                .appendQueryParameter("key", GoogleApiConstants.KEY)
                .build();

        return uri.toString();
    }


    //TODO: Check indico.io API approach, Google NLP has an little problem to recognize capital letters, and can be tricked to a neutral mood by that.

    @Override
    protected okhttp3.Request getRequest() {
        Request request = new Request();
        MoodContent contBean = new MoodContent();
        contBean.setType("PLAIN_TEXT");
        contBean.setContent(text);
        request.setDocument(contBean);
        request.setEncodingType("UTF8");

        Gson gson = new GsonBuilder()
                        .create();

        RequestBody body = RequestBody.create(MediaType.parse("application/json;"),
                                                                gson.toJson(request));
        return new okhttp3.Request.Builder()
                          .url(getUrl())
                          .post(body)
                          .build();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetMoodData extends AsyncTask<Void, Void, Mood> {

        @Override
        protected Mood doInBackground(Void... params) {
            Mood mood = null;

            try {
                Response response = getRequestClient().newCall(getRequest()).execute();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String json = response
                            .body()
                            .string();

                    Gson gson = new GsonBuilder()
                            .create();
                    mood = gson.fromJson(json, getType());
                }

            } catch (IOException e) {
                Log.e(TAG, "Error on request: ", e);
            }

            return mood;
        }

        @Override
        protected void onPostExecute(Mood mood) {
            if (getRequestListener() != null) {
                getRequestListener().onDownloadFinish(mood);
            }
        }
    }


    private class CheckMoodCallback implements RequestListener<Mood> {
        @Override
        public void onDownloadFinish(Mood mood) {
            listener.onDownloadFinish(mood);
        }
    }
}
