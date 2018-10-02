package br.com.andreyneto.ifood_mobile_test.data.network

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import br.com.andreyneto.ifood_mobile_test.ui.main.MainActivityViewModel
import br.com.andreyneto.ifood_mobile_test.ui.main.MainViewModelFactory
import br.com.andreyneto.ifood_mobile_test.utilities.InjectorUtils
import twitter4j.ResponseList
import twitter4j.Status
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class TweetNetworkDataSource private constructor(
        private val mContext: Context,
        private val mExecutors: AppExecutors) {

    private val mDownloadedTweets: MutableLiveData<Array<TweetEntry>> = MutableLiveData()

    val loadingTweets = MutableLiveData<Boolean>()

    val currentTweets: LiveData<Array<TweetEntry>>
        get() = mDownloadedTweets

    internal fun fetchTweet(username: String) {
        Log.d(LOG_TAG, "Fetch weather started")
        mExecutors.networkIO().execute {
            loadingTweets.postValue(true)
            val cb = ConfigurationBuilder()
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("l6uCvnlUegQLb44URZgDSc68l")
                    .setOAuthConsumerSecret("7w95OhKorI983fKEHZ3aNvhCZ6bmoQGW1nmkWmVsaSkUDnIlUt")
                    .setOAuthAccessToken("3025768278-o1GkoQrD8MRTB9ND41Hbunjq4YQK1FpOR1bdaNj")
                    .setOAuthAccessTokenSecret("ovy0TluJfK1ZlCBbTsdPbg0SqN48JyFAdoG47K4PwEPi0")
            val tf = TwitterFactory(cb.build())
            val twitter = tf.instance
            val tweets = mutableListOf<TweetEntry>()
            try {
                val userTimeline = twitter.getUserTimeline(username)
                for (tweet in userTimeline) {
                    tweets.add(TweetEntry(
                            text = tweet.text,
                            username = tweet.user.screenName,
                            tweetID = tweet.id))
                }
            }catch (e: TwitterException) {
                Log.e(LOG_TAG, e::class.java.simpleName)
            }
            loadingTweets.postValue(false)
            mDownloadedTweets.postValue(tweets.toTypedArray())
        }
    }

    companion object {
        private val LOG_TAG = TweetNetworkDataSource::class.java.simpleName

        private val LOCK = Any()
        private var sInstance: TweetNetworkDataSource? = null

        fun getInstance(context: Context, executors: AppExecutors): TweetNetworkDataSource? {
            Log.d(LOG_TAG, "Getting the network data source")
            if (sInstance == null) {
                synchronized(LOCK) {
                    sInstance = TweetNetworkDataSource(context.applicationContext, executors)
                    Log.d(LOG_TAG, "Made new network data source")
                }
            }
            return sInstance
        }
    }

}