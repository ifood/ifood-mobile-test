package com.rafamatias.nlp.domain.repository;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.Sentiment;

public interface SentimentRepository {

    LiveData<Resource<Sentiment>> getSentiment(String text);

}
