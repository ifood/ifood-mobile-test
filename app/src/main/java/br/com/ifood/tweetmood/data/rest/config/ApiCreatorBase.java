package br.com.ifood.tweetmood.data.rest.config;


import br.com.ifood.tweetmood.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by uchoa on 10/06/18.
 */

public class ApiCreatorBase {

    private String GOOGLE_API_URL = BuildConfig.HOST;

    private OkHttpClient httpClient = NetworkSetupBase.getClient();

    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(GOOGLE_API_URL)
            .addConverterFactory(GsonConverterFactory.create());


    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
