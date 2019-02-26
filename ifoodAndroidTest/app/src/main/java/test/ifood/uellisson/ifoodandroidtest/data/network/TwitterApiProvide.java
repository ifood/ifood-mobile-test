package test.ifood.uellisson.ifoodandroidtest.data.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.ifood.uellisson.ifoodandroidtest.Constants;

public class TwitterApiProvide {
    private static Retrofit retrofit;


    public static TwitterAPI provideAndCreate() {
        return provide().create(TwitterAPI.class);
    }

    public static Retrofit provide() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_TWITTER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getDefaultOkHttpClient())
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient getDefaultOkHttpClient() {

       return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            }
        }).build();
    }
}
