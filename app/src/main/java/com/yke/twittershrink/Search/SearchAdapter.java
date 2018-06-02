package com.yke.twittershrink.Search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yke.twittershrink.R;
import com.yke.twittershrink.TweetViewHolder;
import com.yke.twittershrink.Twitter.Tweet;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter {

    private final List<Tweet> tweets;

    SearchAdapter() {
        tweets = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item, parent, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TweetViewHolder viewHolder = (TweetViewHolder) holder;
        Tweet tweet = tweets.get(position);

        viewHolder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    /**
     * Set the tweets list.
     *
     * @param tweets Tweets list.
     */
    void setTweets(List<Tweet> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }
}

