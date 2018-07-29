package com.rafamatias.nlp.domain.interactor;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.data.repository.TweetsDataRepository;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.presentation.model.Tweet;
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

    public LiveData<Resource<List<Tweet>>> getTweets() {
        return tweetRepository.getTweets();
    }
}
