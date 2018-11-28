package com.eblushe.apptwitter.common.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eblushe.apptwitter.common.databases.dao.TweetDAO
import com.eblushe.apptwitter.common.databases.dao.UserDAO
import com.eblushe.apptwitter.common.models.Tweet
import com.eblushe.apptwitter.common.models.User

@Database(version = 1, exportSchema = false, entities = [User::class, Tweet::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun userDao() : UserDAO
    abstract  fun tweetDao() : TweetDAO
}