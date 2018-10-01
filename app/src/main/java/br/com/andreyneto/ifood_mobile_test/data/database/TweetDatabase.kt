package br.com.andreyneto.ifood_mobile_test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TweetEntry::class], version = 1)
abstract class TweetDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao

    companion object {
        private val DATABASE_NAME = "tweet"
        private val LOCK = Any()
        @Volatile
        private var sInstance: TweetDatabase? = null

        fun getInstance(context: Context): TweetDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context.applicationContext,
                                TweetDatabase::class.java, TweetDatabase.DATABASE_NAME).build()
                    }
                }
            }
            return sInstance
        }
    }
}
