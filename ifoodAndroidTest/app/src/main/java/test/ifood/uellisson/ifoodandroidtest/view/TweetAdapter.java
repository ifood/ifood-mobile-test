package test.ifood.uellisson.ifoodandroidtest.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import test.ifood.uellisson.ifoodandroidtest.R;
import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder>{
    Context context;
    List<Tweet> tweetList;

    public TweetAdapter(Context context, List<Tweet> tweetList) {
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
        tweetViewHolder.tweetMessage.setText(tweetList.get(i).getTwitterMessage());
        tweetViewHolder.crateAt.setText(tweetList.get(i).getCreateAt());
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }
}
