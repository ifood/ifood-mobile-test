package br.com.ifood.tweetmood.presentation.view.tweets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.ifood.tweetmood.presentation.component.CircleTransform;
import br.com.ifood.tweetmood.databinding.TweetItemBinding;
import br.com.ifood.tweetmood.domain.model.TweetModel;


public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.ViewHolder> {

    private ArrayList<TweetModel> tweets;
    private OnItemClick onItemClick;


    public TweetListAdapter(ArrayList<TweetModel> tweets, OnItemClick onItemClick) {
        this.tweets = tweets;
        this.onItemClick = onItemClick;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =  LayoutInflater.from(viewGroup.getContext());
        TweetItemBinding itemBinding =
                TweetItemBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TweetModel tweet = tweets.get(position);
        holder.binding.setTweet(tweet);

        Picasso.get()
                .load(tweet.getUrlProfile())
                .transform(new CircleTransform())
                .into(holder.binding.ivAvatar);
    }

    @Override
    public int getItemCount() {
        if (tweets != null)
            return tweets.size();
        else return 0;

    }

    public void updateTweets(ArrayList<TweetModel> newTweets) {
        this.tweets.clear();
        this.tweets.addAll(newTweets);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TweetItemBinding binding;

        public ViewHolder(TweetItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

            binding.clItem.setOnClickListener(view -> onItemClick.onItemClick(tweets.get(getAdapterPosition())));

        }

    }


    public interface OnItemClick {
        void onItemClick(TweetModel tweet);
    }

}