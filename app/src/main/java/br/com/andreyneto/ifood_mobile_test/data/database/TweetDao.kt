package br.com.andreyneto.ifood_mobile_test.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(tweets: List<TweetEntry>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateTweet(tweet: TweetEntry)

    @Query("SELECT * FROM tweets WHERE tweetID = :tweetID")
    fun getTweetByID(tweetID: Long): LiveData<TweetEntry>

    @Query("SELECT * FROM tweets WHERE username = :username ORDER BY tweetID DESC")
    fun getTweetByUser(username: String): LiveData<List<TweetEntry>>

}