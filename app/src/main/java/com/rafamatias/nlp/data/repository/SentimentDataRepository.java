package com.rafamatias.nlp.data.repository;

import com.rafamatias.nlp.data.entity.SentimentRequest;
import com.rafamatias.nlp.data.entity.SentimentResponse;
import com.rafamatias.nlp.data.net.GoogleService;
import com.rafamatias.nlp.domain.repository.SentimentRepository;

import retrofit2.Call;

public class SentimentDataRepository implements SentimentRepository {

    @Override
    public Call<SentimentResponse> getSentiment(String content) {
        return GoogleService.getInstance().analyzeSentiment(SentimentRequest.withContext(content));
    }

}
