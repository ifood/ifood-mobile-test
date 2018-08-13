package bloder.com.twitter.search_user

import android.annotation.SuppressLint
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import bloder.com.domain.models.search.Status
import bloder.com.domain.models.sentiment.SENTIMENT
import bloder.com.presentation.AppViewModel
import bloder.com.presentation.twitter.sentiment.SentimentState
import bloder.com.presentation.twitter.sentiment.SentimentViewModel
import bloder.com.twitter.FullScreenBottomSheet
import bloder.com.twitter.R
import bloder.com.twitter.search_user.adapter.TweetsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searched_user_dialog.view.*
import org.koin.android.ext.android.inject

class SearchedTweetsDialog @SuppressLint("ValidFragment") constructor
(private val tweets: List<Status>, private val search: String) : FullScreenBottomSheet<SentimentState>() {

    constructor() : this(listOf(), "")

    private val viewModel: SentimentViewModel by inject()

    override fun viewToInflate(): Int = R.layout.searched_user_dialog

    override fun onViewInflated(view: View) {
        context?.let { context ->
            view.close.setOnClickListener { dismiss() }
            view.search_text shows search
            view.tweet_list.layoutManager = LinearLayoutManager(context)
            view.tweet_list.adapter = TweetsAdapter(context, tweets)
        }
    }

    override fun handleState(state: SentimentState) = when(state) {
        is SentimentState.SentimentGenerated -> onSentimentGenerated(state.tweet, state.sentiment)
        is SentimentState.ErrorWhenGenerateSentiment -> errorOnGeneratingSentiment(state.errorMessage)
    }

    override fun provideViewModel(): AppViewModel<SentimentState> = viewModel

    private fun onSentimentGenerated(tweet: String, sentiment: SENTIMENT) {

    }

    private fun errorOnGeneratingSentiment(errorMessage: String) {
        context?.let {
            AlertDialog.Builder(it)
                    .setTitle(getString(R.string.ops_error))
                    .setMessage(errorMessage)
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
        }
    }
}

infix fun ImageView.shows(url: String) = Picasso.get().load(url).into(this)

infix fun TextView.shows(text: String) {
    this.text = text
}