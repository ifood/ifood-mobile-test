package com.rafamatias.nlp.domain.repository;

import com.rafamatias.nlp.data.entity.SentimentResponse;

import retrofit2.Call;

public interface SentimentRepository {

    Call<SentimentResponse> getSentiment(String content);

}
