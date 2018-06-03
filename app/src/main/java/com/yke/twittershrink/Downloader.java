package com.yke.twittershrink;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Downloader<T> {

    private static final String TAG = Downloader.class.getSimpleName();

    protected abstract RequestListener<T> getRequestListener();

    protected abstract String getUrl();

    protected abstract Type getType();

    protected OkHttpClient getRequestClient() {
        return new OkHttpClient.Builder().build();
    }

    /**
     *  Return the request:
     */
    protected Request getRequest() {
        return new Request.Builder().url(getUrl()).build();
    }

    /**
     *  Listener to support the request execution.
     *
     *  @param <T> Type of object to be parsed.
     */
    public interface RequestListener<T> {

        /**
         * Called on finish download.
         *
         * @param bean Parsed object.
         */
        void onDownloadFinish(T bean);
    }

    /**
     *  Execute download task:
     */
    @SuppressLint("StaticFieldLeak")
    public class GetData extends AsyncTask<Void, Void, T> {

        @Override
        protected T doInBackground(Void... params) {
            T bean = null;

            try {
                Response response = getRequestClient().newCall(getRequest()).execute();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String json = response
                                  .body()
                                  .string();

                    Gson gson = new GsonBuilder()
                                .create();
                    bean = gson.fromJson(json, getType());
                }

            } catch (IOException e) {
                Log.e(TAG, "Error on request: ", e);
            }

            return bean;
        }

        @Override
        protected void onPostExecute(T bean) {
            if (getRequestListener() != null) {
                getRequestListener().onDownloadFinish(bean);
            }
        }
    }
}
