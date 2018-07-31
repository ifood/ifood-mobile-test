package com.rafamatias.nlp.domain.mapper;


import com.rafamatias.nlp.presentation.model.TweetModel;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TweetMapper {

    public static List<TweetModel> fromTweets(Collection<Tweet> tweets) {
        List<TweetModel> result = new ArrayList<TweetModel>();

        for(com.twitter.sdk.android.core.models.Tweet tweet: tweets){
            result.add(new TweetModel(tweet.text, tweet.createdAt));
        }

        return result;
    }
}
