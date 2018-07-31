package com.rafamatias.nlp.data.net;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class GoogleService {

    private static final String BASE_URL = "https://language.googleapis.com/";

    private static GoogleApi instance;

    public static GoogleApi getInstance() {
        if(instance == null) {
            instance = createService();
        }

        return instance;
    }

    private static GoogleApi createService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(GoogleApi.class);
    }

}
