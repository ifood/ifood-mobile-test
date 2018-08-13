package bloder.com.twitter.search_user

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import bloder.com.domain.models.search.Status
import bloder.com.twitter.FullScreenBottomSheet
import bloder.com.twitter.R
import bloder.com.twitter.search_user.adapter.TweetsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searched_user_dialog.view.*

class SearchedTweetsDialog @SuppressLint("ValidFragment") constructor
(private val tweets: List<Status>, private val search: String) : FullScreenBottomSheet() {

    constructor() : this(listOf(), "")

    override fun viewToInflate(): Int = R.layout.searched_user_dialog

    override fun onViewInflated(view: View) {
        context?.let { context ->
            view.close.setOnClickListener { dismiss() }
            view.search_text shows search
            view.tweet_list.layoutManager = LinearLayoutManager(context)
            view.tweet_list.adapter = TweetsAdapter(context, tweets)
        }
    }
}

infix fun ImageView.shows(url: String) = Picasso.get().load(url).into(this)

infix fun TextView.shows(text: String) {
    this.text = text
}