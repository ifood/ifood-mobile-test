package com.rafamatias.nlp.data.repository;

import com.rafamatias.nlp.data.net.TwitterApi;
import com.rafamatias.nlp.domain.repository.TweetRepository;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;

public class TweetsDataRepository implements TweetRepository {

    private final TwitterApi twitterApi;

    public TweetsDataRepository(){
        this(TwitterApi.getInstance());
    }

    private TweetsDataRepository(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    @Override
    public Call<List<Tweet>> getTweets(String username) {
        return twitterApi.getTimeline(username);
    }

}
