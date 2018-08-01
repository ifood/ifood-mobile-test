package com.rafamatias.nlp.domain.repository;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;

public interface TweetRepository {
    Call<List<Tweet>> getTweets(String username);
}
