package br.com.tweetanalyzer.presenter.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.models.TwitterModel
import br.com.tweetanalyzer.util.DateFormat
import com.bumptech.glide.Glide

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
class TwitterListAdapter(private val context: Context,
                         private var tweets: List<TwitterModel>) :
        RecyclerView.Adapter<TwitterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.twitter_list_row, parent, false))

    override fun getItemCount(): Int = tweets.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]

        holder.screenName.text = tweet.userInfo.screenName
        holder.retweetCount.text = tweet.retweetCount
        holder.likesCount.text = tweet.tweetLikesCount
        holder.tweetText.text = tweet.description

        holder.tweetCreatedAt.text = DateFormat().convertDate(tweet.createAt)


//        setTweetImage(holder.tweetImage, tweet.tweetImageUrl)
    }

    private fun setTweetImage(imageView: ImageView, tweetImageUrl: String?) {
        if (tweetImageUrl == null || tweetImageUrl.isEmpty())
            imageView.visibility = View.GONE
        else
            Glide.with(context)
                    .load(tweetImageUrl)
                    .into(imageView)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var screenName: TextView = v.findViewById(R.id.screen_name)
        var retweetCount: TextView = v.findViewById(R.id.retweet_count)
        var likesCount: TextView = v.findViewById(R.id.likes_count)
        var tweetText: TextView = v.findViewById(R.id.tweet_text)
        val tweetImage: ImageView = v.findViewById(R.id.tweet_image)
        val tweetCreatedAt: TextView = v.findViewById(R.id.create_at)
    }
}