package br.com.ifood.tweetmood.data.rest.config;

import java.util.concurrent.TimeUnit;

import br.com.ifood.tweetmood.BuildConfig;
import br.com.ifood.tweetmood.TMApplication;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by uchoa on 10/06/18.
 */

public class NetworkSetupBase {

    private static final String KEY_QUERY = "key";

    public static final OkHttpClient getClient() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(requestIntercept)
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return mLogging;
    }


    public static final Interceptor requestIntercept = chain -> {
        if (TMApplication.getInstance().isOnline()){

            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(KEY_QUERY, BuildConfig.GOOGLE_KEY)
                    .build();

            Request request = original.newBuilder()
                    .addHeader("Content-Type" , "application/json")
                    .url(url)
                    .build();

            return chain.proceed(request);
        } else
            throw new NoConnectivityException();
    };


}


