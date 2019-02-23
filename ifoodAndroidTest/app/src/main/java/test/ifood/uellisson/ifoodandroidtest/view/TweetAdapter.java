package test.ifood.uellisson.ifoodandroidtest.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import test.ifood.uellisson.ifoodandroidtest.ConstantsUtil;
import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder>{
    Context context;
    List<Tweet> tweetList;

    public TweetAdapter(Activity context, List<Tweet> tweetList) {
        super();
        this.context = context;
        this.tweetList = tweetList;
    }


    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweet_item, viewGroup, false);
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder tweetViewHolder, int i) {
        final Tweet tweet = tweetList.get(i);
        tweetViewHolder.tweetMessage.setText(tweet.getTwitterMessage());
        tweetViewHolder.crateAt.setText(tweet.getCreateAt());
        clickItem(tweetViewHolder, tweet);
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public void clickItem (TweetViewHolder tweetViewHolder, final Tweet tweet) {
        tweetViewHolder.bgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TweetDetailActivity.class);
                intent.putExtra(ConstantsUtil.TWEET_MESSAGE, tweet.getTwitterMessage());
                intent.putExtra(ConstantsUtil.CREATE_AT, tweet.getCreateAt());

                context.startActivity(intent);
            }
        });
    }
}
