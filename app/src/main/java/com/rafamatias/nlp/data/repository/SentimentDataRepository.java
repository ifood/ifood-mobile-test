package com.rafamatias.nlp.data.repository;

import com.rafamatias.nlp.data.entity.SentimentRequest;
import com.rafamatias.nlp.data.entity.SentimentResponse;
import com.rafamatias.nlp.data.net.GoogleService;
import com.rafamatias.nlp.domain.repository.SentimentRepository;

import retrofit2.Call;

// TODO: Implement database data source. It is not necessary analyze sentiment of a Tweet again
public class SentimentDataRepository implements SentimentRepository {

    @Override
    public Call<SentimentResponse> getSentiment(String content) {
        return GoogleService.getInstance().analyzeSentiment(SentimentRequest.withContext(content));
    }

}
