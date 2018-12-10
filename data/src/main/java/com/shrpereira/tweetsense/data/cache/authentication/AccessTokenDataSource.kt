package com.shrpereira.tweetsense.data.cache.authentication

import android.content.Context
import android.content.SharedPreferences
import com.shrpereira.tweetsense.data.cache.common.apply

private const val AUTH_PREFS = "authPrefs"
private const val TWITTER_AUTH_TOKEN = "twitterAuthToken"
private const val GOOGLE_AUTH_TOKEN = "googleAuthToken"

interface AccessTokenDataSource {

	fun getTwitterAccessToken(): String?
	fun saveTwitterAccessToken(accessToken: String)

	fun getGoogleAccessToken(): String?
	fun saveGoogleAccessToken(accessToken: String)
}

class AccessTokenDataSourceImpl(private val context: Context) : AccessTokenDataSource {

	override fun getTwitterAccessToken(): String? {
		return getSharedPrefs(AUTH_PREFS).getString(TWITTER_AUTH_TOKEN, null)
	}

	override fun saveTwitterAccessToken(accessToken: String) {
		getSharedPrefs(AUTH_PREFS).apply {
			remove(TWITTER_AUTH_TOKEN)
			putString(TWITTER_AUTH_TOKEN, accessToken)
		}
	}

	override fun getGoogleAccessToken(): String? {
		return getSharedPrefs(AUTH_PREFS).getString(GOOGLE_AUTH_TOKEN, null)
	}

	override fun saveGoogleAccessToken(accessToken: String) {
		getSharedPrefs(AUTH_PREFS).apply {
			remove(GOOGLE_AUTH_TOKEN)
			putString(GOOGLE_AUTH_TOKEN, accessToken)
		}
	}

	private fun getSharedPrefs(name: String): SharedPreferences {
		return context.getSharedPreferences(name, Context.MODE_PRIVATE)
	}
}