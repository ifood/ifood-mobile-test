package movile.marcus.com.br.moviletest.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tweet.view.*
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.data.TweetData
import movile.marcus.com.br.moviletest.ui.BaseRecyclerAdapter
import movile.marcus.com.br.moviletest.util.CircleTransform

class HomeTweetListAdapter : BaseRecyclerAdapter<TweetData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = recyclerList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(tweetData: TweetData) = with(view) {
            Picasso
                .get()
                .load(tweetData.user?.profileImageUrl)
                .transform(CircleTransform())
                .into(itemTweetImage)
            itemTweetUser.text = tweetData.user?.screenName
            itemTweetText.text = tweetData.text
        }
    }
}