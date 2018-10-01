package br.com.andreyneto.ifood_mobile_test.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.andreyneto.ifood_mobile_test.AppExecutors
import br.com.andreyneto.ifood_mobile_test.data.database.TweetDao
import br.com.andreyneto.ifood_mobile_test.data.database.TweetEntry
import br.com.andreyneto.ifood_mobile_test.data.network.TweetNetworkDataSource

class TweetRepository private constructor(private val mTweetDao: TweetDao,
                                             private val mTweetNetworkDataSource:TweetNetworkDataSource,
                                             private val mExecutors: AppExecutors) {
    private var mInitialized = false

    init{
        val networkData = mTweetNetworkDataSource.currentTweets
        networkData.observeForever {
            newTweetsFromNetwork: Array<TweetEntry>-> mExecutors.diskIO().execute {
                mTweetDao.bulkInsert(newTweetsFromNetwork.toList())
                Log.d(LOG_TAG, "New values inserted")
            }
        }
    }


    fun getTweetByID(tweetId:String): LiveData<TweetEntry> {
        //TODO initializeData()
        return mTweetDao.getTweetByID(tweetId)
    }

    fun getTweetByUser(username:String):LiveData<List<TweetEntry>> {
        mTweetNetworkDataSource.fetchTweet(username)
        return mTweetDao.getTweetByUser(username)
    }

    companion object {
        private val LOG_TAG = TweetRepository::class.java.simpleName

        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance:TweetRepository? = null

        @Synchronized  fun getInstance(
                tweetDao:TweetDao, tweetNetorkDataSource: TweetNetworkDataSource,
                executors:AppExecutors): TweetRepository? {
            Log.d(LOG_TAG, "Getting the repository")
            if (sInstance == null)
            {
                synchronized (LOCK) {
                    sInstance = TweetRepository(tweetDao, tweetNetorkDataSource,
                            executors)
                    Log.d(LOG_TAG, "Made new repository")
                }
            }
            return sInstance
        }
    }

}