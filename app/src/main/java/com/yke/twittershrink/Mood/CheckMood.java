package com.yke.twittershrink.Mood;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yke.twittershrink.Const.AppConstants;
import com.yke.twittershrink.Downloader;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CheckMood extends Downloader<Mood> {

    private String text;

    private RequestListener<Mood> listener;

    public void analyzeText(String text, RequestListener<Mood> listener) {
        this.text = text;
        this.listener = listener;
        new GetData().execute();
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
        Uri uri = Uri.parse(AppConstants.ANALYZE_SENTIMENT_URL)
                .buildUpon()
                .appendQueryParameter("key", AppConstants.KEY)
                .build();

        return uri.toString();
    }

    @Override
    protected okhttp3.Request getRequest() {
        Request request = new Request();
        MoodContent contBean = new MoodContent();
        contBean.setType("PLAIN_TEXT");
        contBean.setContent(text);
        request.setDocument(contBean);
        request.setEncodingType("UTF8");

        Gson gson = new Gson();

        RequestBody body = RequestBody.create(MediaType.parse("application/json;"),
                                                                gson.toJson(request));
        return new okhttp3.Request.Builder()
                          .url(getUrl())
                          .post(body)
                          .build();
    }


    private class CheckMoodCallback implements RequestListener<Mood> {
        @Override
        public void onFinishDownload(Mood mood) {
            listener.onFinishDownload(mood);
        }
    }
}
