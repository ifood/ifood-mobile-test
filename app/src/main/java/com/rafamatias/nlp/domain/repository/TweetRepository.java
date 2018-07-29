package com.rafamatias.nlp.domain.repository;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.presentation.model.TweetModel;
import com.rafamatias.nlp.domain.Resource;

import java.util.List;

public interface TweetRepository {
    LiveData<Resource<List<TweetModel>>> getTweets(String username);
}
