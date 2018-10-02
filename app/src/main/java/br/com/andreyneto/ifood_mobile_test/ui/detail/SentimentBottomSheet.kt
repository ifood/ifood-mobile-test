package br.com.andreyneto.ifood_mobile_test.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.andreyneto.ifood_mobile_test.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import br.com.andreyneto.ifood_mobile_test.utilities.InjectorUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_sentiment.*


class SentimentBottomSheet : BottomSheetDialogFragment() {

    companion object {
        val TWEET_ID = "TWEET_ID"
    }

    private var mTweet: TweetEntry? = null
    private var mViewModel: SentimentViewModel? = null

    fun newInstance(tweetID: Long): SentimentBottomSheet {
        val dialog = SentimentBottomSheet()
        val bundle = Bundle()
        bundle.putLong(TWEET_ID, tweetID)
        dialog.arguments = bundle
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tweetID = arguments!!.getLong(TWEET_ID)
        val factory: SentimentViewModelFactory = InjectorUtils().provideSentimentViewModelFactory(activity!!, tweetID)
        mViewModel = ViewModelProviders.of(this, factory).get(SentimentViewModel::class.java)

        mViewModel!!.loadingSentiment().observe(this, Observer {
            showLoading(it)
        })


        mViewModel!!.getTweet().observe(this, Observer {
            manageTweet(it)
        })
    }

    private fun manageTweet(tweet: TweetEntry) {
        lblTweet.text = tweet.text
        if(tweet.sentimentChecked) {
            when {
                tweet.score < -0.25 -> {
                    lblSentiment.text = "😐"
                    sheetBackground.setBackgroundColor(ContextCompat.getColor(this.context!!, R.color.colorSad))
                }
                tweet.score > 0.25 -> {
                    lblSentiment.text = "\uD83D\uDE03"
                    sheetBackground.setBackgroundColor(ContextCompat.getColor(this.context!!, R.color.colorHappy))
                }
                else -> {
                    lblSentiment.text = "😐"
                    sheetBackground.setBackgroundColor(ContextCompat.getColor(this.context!!, R.color.colorNeutral))
                }
            }
        } else {
            mViewModel!!.getSentiment(tweet.text).observe(this, Observer {
                tweet.score = it
                tweet.sentimentChecked = true
                mViewModel!!.update(tweet)
            })
        }

        //Log.e("$it - ${response.language} - $text", if (it > 0.25)"feliz" else if (it < -0.25) "triste" else "neutro")
    }

    private fun showLoading(status: Boolean) {
        progressBar.visibility = if(status) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_sentiment, container, false)
    }
}