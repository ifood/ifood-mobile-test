package com.eblushe.apptwitter.common.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eblushe.apptwitter.common.databases.dao.UserDAO
import com.eblushe.apptwitter.common.models.User

@Database(version = 1, exportSchema = false, entities = [User::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun userDao() : UserDAO
}