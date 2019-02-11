package com.drss.ifoodmobiletest.view

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.drss.ifoodmobiletest.R
import com.drss.ifoodmobiletest.data.enums.MoodEnum
import com.drss.ifoodmobiletest.databinding.ActivitySentimentBinding
import com.drss.ifoodmobiletest.viewmodel.SentimentViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_sentiment.*
import javax.inject.Inject

class SentimentActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var injectableViewModelFactory: ViewModelProvider.Factory
    lateinit var binding: ActivitySentimentBinding

    private lateinit var sentimentViewModel: SentimentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sentiment)
        setupViewModel()

        val tweetText = intent.getStringExtra("TWEET_TEXT")
        val tweetDate = intent.getStringExtra("TWEET_DATE")
        sentimentViewModel.text.value = tweetText
        sentimentViewModel.date.value = tweetDate
        sentimentViewModel.textSentimentType.observe(this, Observer { sentiment -> onSentimentAnalyzed(sentiment) })
    }

    private fun setupViewModel() {
        sentimentViewModel = ViewModelProviders.of(this, injectableViewModelFactory)[SentimentViewModel::class.java]
        binding.viewModel = sentimentViewModel
    }

    private fun onSentimentAnalyzed(type: MoodEnum) {
        val backGroundColor = resources.getColor(R.color.background_color)
        var nextBackgroundColor = resources.getColor(R.color.background_color)

        var emojiText = ""
        when (type) {
            MoodEnum.HAPPY -> {
                emojiText = getString(R.string.happy_emoji)
                tweet_text.setTextColor(Color.BLACK)
                tweet_date.setTextColor(Color.BLACK)
                backButton.imageTintList = ColorStateList.valueOf(Color.BLACK)
                nextBackgroundColor = resources.getColor(R.color.happyColor)
            }
            MoodEnum.SAD -> {
                emojiText = getString(R.string.sad_emoji)
                nextBackgroundColor = resources.getColor(R.color.sadColor)
            }
            MoodEnum.NEUTRAL -> {
                emojiText = getString(R.string.neutral_emoji)
                nextBackgroundColor = resources.getColor(R.color.neutralColor)
            }
        }
        emoji_textview.text = emojiText
        ObjectAnimator.ofObject(
            sentiment_root,
            "backgroundColor",
            ArgbEvaluator(),
            backGroundColor,
            nextBackgroundColor
        ).setDuration(300).start()
    }

    public fun onBackClick(v: View){
        finish()
    }
}