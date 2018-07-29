package com.rafamatias.nlp.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rafamatias.nlp.data.mapper.TweetDataMapper;
import com.rafamatias.nlp.data.net.TwitterApi;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.repository.TweetRepository;
import com.rafamatias.nlp.presentation.model.TweetModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetsDataRepository implements TweetRepository {

    private TwitterApi twitterApi;

    private MutableLiveData<Resource<List<TweetModel>>> tweets = new MutableLiveData<>();


    public TweetsDataRepository(){
        this(TwitterApi.getInstance());
    }

    private TweetsDataRepository(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    @Override
    public LiveData<Resource<List<TweetModel>>> getTweets(String username) {
        tweets.setValue(Resource.<List<TweetModel>>loading());

        loadTimeline(username);

        return tweets;
    }

    private void loadTimeline(String username){
        twitterApi.getTimeline(username).enqueue(getUserTimelineCallback());
    }

    private Callback<List<Tweet>> getUserTimelineCallback(){
        return new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<TweetModel> data = TweetDataMapper.fromTweets(result.data);
                tweets.postValue(Resource.success(data));
            }

            @Override
            public void failure(TwitterException exception) {
                tweets.postValue(Resource.<List<TweetModel>>error(exception.getMessage()));
            }
        };
    }


}
