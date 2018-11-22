package com.eblushe.apptwitter.common.providers

import android.content.SharedPreferences

object StorageProvider {
    private lateinit var preferences: SharedPreferences

    fun init(preferences: SharedPreferences) {
        this.preferences = preferences
    }


    fun writePreference(block: (editor: SharedPreferences.Editor) -> Unit) {
        val editor = preferences.edit()
        block(editor)
        editor.apply()
    }

    fun readPreference(callback: (preferences: SharedPreferences) -> Unit) {
        callback(preferences)
    }
}