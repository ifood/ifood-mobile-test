package com.rafamatias.nlp.data.net;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.List;

import retrofit2.Call;

public class TwitterApi {
    private static final StatusesService statusService = TwitterCore.getInstance().getGuestApiClient().getStatusesService();

    private static TwitterApi instance;

    public static TwitterApi getInstance(){
        if(instance == null){
            instance = new TwitterApi();
        }

        return instance;
    }

    private TwitterApi(){}

    public Call<List<Tweet>> getTimeline(String username) {
        return statusService.userTimeline(null, username, 20,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
