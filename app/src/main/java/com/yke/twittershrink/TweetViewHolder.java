package com.yke.twittershrink;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yke.twittershrink.Twitter.Tweet;

public class TweetViewHolder extends RecyclerView.ViewHolder {

    private final TextView text;

    private final View cardView;

    private final TweetViewInterface presenter;

    public TweetViewHolder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.tweet_item_text);
        cardView = itemView.findViewById(R.id.card_view);
        presenter = new TweetPresenter();
    }

    public void bind(Tweet tweet) {
        text.setText(tweet.getText());
        cardView.setOnClickListener(new ItemClick(tweet));
    }

    private class ItemClick implements View.OnClickListener {

        private final Tweet tweet;

        ItemClick(Tweet tweet) {
            this.tweet = tweet;
        }

        @Override
        public void onClick(View view) {
            presenter.onTweetClicked(view, tweet.getText());
        }
    }
}

