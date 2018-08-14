package bloder.com.twitter.search_user

import android.annotation.SuppressLint
import android.app.ProgressDialog
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
    private val sentimentProgress by lazy { ProgressDialog(context) }

    override fun viewToInflate(): Int = R.layout.searched_user_dialog

    override fun onViewInflated(view: View) {
        context?.let { context ->
            view.close.setOnClickListener { dismiss() }
            view.search_text shows search
            view.tweet_list.layoutManager = LinearLayoutManager(context)
            view.tweet_list.adapter = TweetsAdapter(context, tweets) { tweet ->
                generateSentimentWith(tweet.tweet)
            }
        }
    }

    override fun handleState(state: SentimentState) = when(state) {
        is SentimentState.SentimentGenerated -> onSentimentGenerated(state.sentiment)
        is SentimentState.ErrorWhenGenerateSentiment -> errorOnGeneratingSentiment(state.errorMessage)
    }

    override fun provideViewModel(): AppViewModel<SentimentState> = viewModel

    private fun onSentimentGenerated(sentiment: SENTIMENT) {
        if (sentimentProgress.isShowing) sentimentProgress.dismiss()
        SentimentDialog.get(sentiment).show(activity?.supportFragmentManager, "")
    }

    private fun errorOnGeneratingSentiment(errorMessage: String) {
        if (sentimentProgress.isShowing) sentimentProgress.dismiss()
        context?.let {
            AlertDialog.Builder(it)
                    .setTitle(getString(R.string.ops_error))
                    .setMessage(errorMessage)
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog.dismiss()
                    }.show()
        }
    }

    private fun generateSentimentWith(tweet: String) {
        sentimentProgress.setTitle("Sentiment")
        sentimentProgress.setMessage("Calculating tweet sentiment")
        sentimentProgress.setCancelable(false)
        sentimentProgress.show()
        viewModel.getSentimentFor(tweet)
    }
}

infix fun ImageView.shows(url: String) = Picasso.get().load(url).into(this)
infix fun ImageView.shows(drawable: Int) = Picasso.get().load(drawable).into(this)
infix fun TextView.shows(text: String) { this.text = text }