package com.rafamatias.nlp.domain.repository;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.presentation.model.Tweet;
import com.rafamatias.nlp.domain.Resource;

import java.util.List;

public interface TweetRepository {

    LiveData<Resource<List<Tweet>>> getTweets();
}
