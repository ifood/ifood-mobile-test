package com.eblushe.apptwitter.common.providers

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.eblushe.apptwitter.common.databases.AppDatabase

object StorageProvider {
    lateinit var database: AppDatabase
    private lateinit var preferences: SharedPreferences

    fun init(applicationContext: Context, preferences: SharedPreferences) {
        this.preferences = preferences
        this.database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "AppDatabase").build()

    }

    fun initDatabaseMock(applicationContext: Context) {
        this.database = Room.inMemoryDatabaseBuilder(applicationContext, AppDatabase::class.java).build()
    }

    fun writePreference(block: (editor: SharedPreferences.Editor) -> Unit) {
        val editor = preferences.edit()
        block(editor)
        editor.apply()
    }

    fun readPreference(callback: (preferences: SharedPreferences) -> Unit) {
        callback(preferences)
    }

    fun getDb() : AppDatabase = database
}