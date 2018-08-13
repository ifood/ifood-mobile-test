package bloder.com.twitter.search_user.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import bloder.com.domain.models.search.Status
import bloder.com.twitter.R
import bloder.com.twitter.search_user.shows
import kotlinx.android.synthetic.main.tweet_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class TweetsAdapter(private val context: Context, private val tweets: List<Status>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(context, parent)

    override fun getItemCount(): Int = tweets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(tweets[position])

    class ViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.tweet_layout, parent, false)) {

        fun bind(tweet: Status) {
            itemView.setOnClickListener {}
            itemView.user_image shows tweet.user.profileImage
            itemView.user_name shows tweet.user.name
            itemView.user_screen_name shows "@${ tweet.user.screenName }"
            itemView.tweet shows tweet.tweet
            itemView.tweet_date shows readableDate(tweet.createdAt)
        }

        private fun readableDate(date: String) : String {
            val formatter = SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
            val dateFormatted = formatter.parse(date) as Date
            val cal = Calendar.getInstance()
            cal.time = dateFormatted
            return "${cal.get(Calendar.DATE)}/${(cal.get(Calendar.MONTH) + 1)}/${cal.get(Calendar.YEAR)}"
        }
    }
}