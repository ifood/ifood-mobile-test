package com.eblushe.apptwitter.common.databases.dao

import androidx.room.*
import com.eblushe.apptwitter.common.models.Tweet
import io.reactivex.Observable

@Dao
interface TweetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(tweet: Tweet): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(tweet: List<Tweet>)

    @Update
    fun update(tweet: Tweet) : Int

    @Delete
    fun delete(tweet: Tweet) : Int

    @Query("select * from Tweet where id = :id")
    fun findById(id: Long) : Observable<Tweet>

    @Query("select * from Tweet where userName = :userName")
    fun findByName(userName: String): Observable<List<Tweet>>
}
