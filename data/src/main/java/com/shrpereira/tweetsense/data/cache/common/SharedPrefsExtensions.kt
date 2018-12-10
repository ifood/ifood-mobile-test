package com.shrpereira.tweetsense.data.cache.common

import android.content.SharedPreferences

inline fun SharedPreferences.apply(modifier: SharedPreferences.Editor.() -> Unit) {
	val editor = this.edit()
	editor.modifier()
	editor.apply()
}