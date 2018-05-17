package br.com.tweetanalyzer

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class PreferenceController {

    companion object {
        private const val PREFERENCE_NAME = "twitter_analyzer_pref"
        private const val TOKEN = "token"
        private const val TOKEN_TYPE = "token_type"

        private fun getPreference(context: Context): SharedPreferences =
                context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

        fun setToken(context: Context, token: String) = getPreference(context).edit().putString(TOKEN, token).apply()

        fun getToken(context: Context): String = getPreference(context).getString(TOKEN, "")

        fun setTokenType(context: Context, tokenType: String) = getPreference(context).edit().putString(TOKEN_TYPE, tokenType).apply()

        fun getTokenType(context: Context) = getPreference(context).getString(TOKEN_TYPE, "")
    }
}