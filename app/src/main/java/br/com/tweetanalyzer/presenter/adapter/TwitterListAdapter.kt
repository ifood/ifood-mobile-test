package br.com.tweetanalyzer.presenter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.models.TwitterModel
import br.com.tweetanalyzer.util.DateFormat

/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
class TwitterListAdapter(private var tweets: List<TwitterModel>,
                         private var callback: OnItemClicked) :
        RecyclerView.Adapter<TwitterListAdapter.ViewHolder>() {

    interface OnItemClicked {
        fun onItemClicked(item: TwitterModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.twitter_list_row, parent, false))

    override fun getItemCount(): Int = tweets.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val it = tweets[position]

        holder.screenName.text = it.userInfo.screenName
        holder.retweetCount.text = it.retweetCount
        holder.likesCount.text = it.tweetLikesCount
        holder.tweetText.text = it.description

        holder.tweetCreatedAt.text = DateFormat().convertDate(it.createAt)

        holder.bindClick(it, callback)
    }

    fun setContent(tweetList: List<TwitterModel>) {
        tweets = tweetList
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(v: View?) {
            if (callback != null && item != null)
                callback!!.onItemClicked(item!!)
        }

        var screenName: TextView = v.findViewById(R.id.screen_name)
        var retweetCount: TextView = v.findViewById(R.id.retweet_count)
        var likesCount: TextView = v.findViewById(R.id.likes_count)
        var tweetText: TextView = v.findViewById(R.id.tweet_text)
        val tweetCreatedAt: TextView = v.findViewById(R.id.create_at)

        private var callback: OnItemClicked? = null
        private var item: TwitterModel? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindClick(item: TwitterModel, callback: OnItemClicked) {
            this.callback = callback
            this.item = item
        }
    }

}