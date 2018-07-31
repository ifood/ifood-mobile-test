package com.rafamatias.nlp.domain.interactor;

import android.arch.lifecycle.MediatorLiveData;

import com.rafamatias.nlp.data.repository.TweetsDataRepository;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.mapper.TweetMapper;
import com.rafamatias.nlp.domain.repository.TweetRepository;
import com.rafamatias.nlp.presentation.model.TweetModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class GetTweetsInteractor extends MediatorLiveData<Resource<List<TweetModel>>> {

    private final TweetRepository tweetRepository;

    public GetTweetsInteractor() {
        this.tweetRepository = new TweetsDataRepository();
    }

    public GetTweetsInteractor(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void getTweets(String username) {
        setValue(Resource.<List<TweetModel>>loading());
        tweetRepository.getTweets(username).enqueue(callback);
    }

    private Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
        @Override
        public void success(Result<List<Tweet>> result) {
            List<TweetModel> data = TweetMapper.fromTweets(result.data);
            setValue(Resource.success(data));
        }

        @Override
        public void failure(TwitterException exception) {
            setValue(Resource.<List<TweetModel>>error(exception.getMessage()));
        }
    };

}
