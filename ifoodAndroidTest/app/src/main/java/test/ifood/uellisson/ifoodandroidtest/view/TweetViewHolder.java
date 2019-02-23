package test.ifood.uellisson.ifoodandroidtest.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import test.ifood.uellisson.ifoodandroidtest.R;

public class TweetViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tweet_message)
    TextView tweetMessage;

    @Bind(R.id.create_at)
    TextView crateAt;

    public TweetViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
