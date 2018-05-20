package br.com.tweetanalyzer.presenter.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.tweetanalyzer.GNaturalApi.NaturalLanguageConstant
import br.com.tweetanalyzer.R
import br.com.tweetanalyzer.SearchService
import br.com.tweetanalyzer.events.AnalyseSearchResult
import br.com.tweetanalyzer.models.JobType
import br.com.tweetanalyzer.models.TwitterModel
import br.com.tweetanalyzer.util.Constant
import br.com.tweetanalyzer.util.DateFormat
import br.com.tweetanalyzer.util.EmotionUtils
import com.dd.processbutton.iml.ActionProcessButton
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
class TwitterListAdapter(private val context: Context,
                         private var tweets: List<TwitterModel>) :
        RecyclerView.Adapter<TwitterListAdapter.ViewHolder>() {

    init {
        EventBus.getDefault().register(this)
    }

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

        if (tweet.score != -2.0)
            setSentimentView(tweet, holder)

        holder.analyseButton.setOnClickListener({
            //Init the button animation
            holder.analyseButton.progress = 50

            val i = Intent(context, SearchService::class.java)
            i.putExtra(Constant.JOB_TYPE, JobType.ANALYSE_SENTIMENT.name)
            i.putExtra(Constant.ANALYSE_SENTIMENT, Gson().toJson(tweet))
            context.startService(i)
        })
    }

    private fun setSentimentView(tweet: TwitterModel, holder: ViewHolder) {
        var colorId = 0
        var textSentiment: CharSequence? = null
        var textColorId = 0
        when {
            tweet.score in NaturalLanguageConstant.GOD_SCORE -> {
                colorId = R.color.good_sentiment_color
                textColorId = R.color.happy_text_color
                textSentiment = context.getString(R.string.happy_twitter) + EmotionUtils().getEmotion(EmotionUtils.HAPPY_EMOTION)
            }
            tweet.score in NaturalLanguageConstant.NORMAL_SCORE -> {
                colorId = R.color.normal_sentiment_color
                textColorId = R.color.neutral_text_color
                textSentiment = context.getString(R.string.neutral_twiiter) + EmotionUtils().getEmotion(EmotionUtils.NEUTRAL_EMOTION)
            }
            tweet.score in NaturalLanguageConstant.BAD_SCORE -> {
                colorId = R.color.bad_sentiment_color
                textColorId = R.color.sad_text_color
                textSentiment = context.getString(R.string.sad_twitter) + EmotionUtils().getEmotion(EmotionUtils.SAD_EMOTION)
            }
        }
        if (colorId != 0 && textSentiment != null) {
            holder.analyseButton.text = textSentiment.toString()
            holder.analyseButton.setBackgroundColor(ContextCompat.getColor(context, colorId))

            if (textColorId != 0)
                holder.analyseButton.setTextColor(ContextCompat.getColor(context, textColorId))
        }
    }


    fun setTweetList(tweetList: List<TwitterModel>) {
        tweets = tweetList
        notifyDataSetChanged()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(analyseResult: AnalyseSearchResult) {
        if (analyseResult.result != null) {
            val index = tweets.indexOf(analyseResult.item)
            if (index != -1) {
                val it = tweets.get(index)
                it.score = analyseResult.result.documentSentiment.score
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var screenName: TextView = v.findViewById(R.id.screen_name)
        var retweetCount: TextView = v.findViewById(R.id.retweet_count)
        var likesCount: TextView = v.findViewById(R.id.likes_count)
        var tweetText: TextView = v.findViewById(R.id.tweet_text)
        val tweetCreatedAt: TextView = v.findViewById(R.id.create_at)
        val analyseButton: ActionProcessButton = v.findViewById(R.id.btnProcess)

        init {
            analyseButton.setMode(ActionProcessButton.Mode.ENDLESS)
            analyseButton.progress = 0
        }
    }

}