package com.rafamatias.nlp.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.repository.SentimentRepository;
import com.rafamatias.nlp.presentation.model.Sentiment;

public class SentimentDataRepository implements SentimentRepository {

    @Override
    public LiveData<Resource<Sentiment>> getSentiment(String text) {

        MutableLiveData<Resource<Sentiment>> result = new MutableLiveData<>();
        result.setValue(Resource.success(new Sentiment(0.2f, 0.2f)));
        return result;

    }

}
