package br.com.andreyneto.ifood_mobile_test.data.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.ifood_mobile_test.App
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import twitter4j.TwitterException

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
            val twitter = (mContext.applicationContext as App).twitter
            val tweets = mutableListOf<TweetEntry>()
            try {
                val userTimeline = twitter!!.getUserTimeline(username)
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