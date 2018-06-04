package com.yke.twittershrink;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public abstract class Downloader<T> {

    protected abstract RequestListener<T> getRequestListener();

    protected abstract String getUrl();

    protected abstract Type getType();
    
    //TODO: Check http://loopj.com/android-async-http/

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
}
