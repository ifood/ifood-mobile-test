package com.rafamatias.nlp.data.net;

import com.rafamatias.nlp.BuildConfig;
import com.rafamatias.nlp.data.entity.SentimentRequest;
import com.rafamatias.nlp.data.entity.SentimentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GoogleApi {

    // TODO: move GOOGLE_KEY to retrofit interceptor
    @POST("v1beta2/documents:analyzeSentiment?key=" + BuildConfig.GOOGLE_KEY)
    Call<SentimentResponse> analyzeSentiment(@Body SentimentRequest request);
}
