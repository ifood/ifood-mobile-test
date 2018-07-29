package com.rafamatias.nlp.domain.interactor;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.data.repository.TweetsDataRepository;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.TweetModel;
import com.rafamatias.nlp.domain.repository.TweetRepository;

import java.util.List;

public class GetTweetsInteractor {
    private final TweetRepository tweetRepository;

    public GetTweetsInteractor() {
        this.tweetRepository = new TweetsDataRepository();
    }

    public GetTweetsInteractor(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public LiveData<Resource<List<TweetModel>>> getTweets(String username) {
        return tweetRepository.getTweets(username);
    }
}
