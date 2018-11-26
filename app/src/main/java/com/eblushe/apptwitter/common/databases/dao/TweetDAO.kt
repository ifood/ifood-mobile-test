package com.eblushe.apptwitter.common.databases.dao

import androidx.room.*
import com.eblushe.apptwitter.common.models.Tweet
import io.reactivex.Single

@Dao
interface TweetDAO {
    @Insert
    fun save(tweet: Tweet): Long

    @Update
    fun update(tweet: Tweet) : Int

    @Delete
    fun delete(tweet: Tweet) : Int

    @Query("select * from Tweet where id = :id")
    fun findById(id: Long) : Single<Tweet>

    @Query("select * from Tweet where userName = :userName")
    fun findByName(userName: String): Single<Tweet>
}
