package com.rafamatias.nlp.domain.interactor;

import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.rafamatias.nlp.data.entity.SentimentResponse;
import com.rafamatias.nlp.data.repository.SentimentDataRepository;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.mapper.SentimentMapper;
import com.rafamatias.nlp.domain.repository.SentimentRepository;
import com.rafamatias.nlp.presentation.model.SentimentModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSentimentInteractor extends MediatorLiveData<Resource<SentimentModel>> {

    private final SentimentRepository sentimentRepository;

    public GetSentimentInteractor() {
        this.sentimentRepository = new SentimentDataRepository();
    }

    public void getSentiment(String content) {
        if(content == null || content.isEmpty()){
            postValue(Resource.error("Content cannot be null"));
        }

        postValue(Resource.loading());

        this.sentimentRepository.getSentiment(content).enqueue(callback);
    }

    private Callback<SentimentResponse> callback = new Callback<SentimentResponse>() {
        @Override
        public void onResponse(@NonNull Call<SentimentResponse> call, @NonNull Response<SentimentResponse> response) {
            SentimentModel model = SentimentMapper.fromSentiment(response.body());
            postValue(Resource.success(model));
        }

        @Override
        public void onFailure(@NonNull Call<SentimentResponse> call, Throwable t) {
            postValue(Resource.error(t.getMessage()));
        }
    };

}
