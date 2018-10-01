package br.com.andreyneto.ifood_mobile_test.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(tweets: List<TweetEntry>)

    @Update
    fun updateTweet(tweet: TweetEntry)

    @Query("SELECT * FROM tweets WHERE tweetID = :tweetID")
    fun getTweetByID(tweetID: String): LiveData<TweetEntry>

    @Query("SELECT * FROM tweets WHERE username = :username")
    fun getTweetByUser(username: String): LiveData<List<TweetEntry>>

}