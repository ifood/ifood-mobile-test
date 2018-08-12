package bloder.com.twitter.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_REF = "twitter_pref"

private fun preferences(context: Context) : SharedPreferences = context.getSharedPreferences(PREFERENCE_REF, Context.MODE_PRIVATE)

fun save(context: Context, key: String, value: String) = preferences(context)
        .edit()
        .putString(key, value)
        .apply()

fun fetch(context: Context, key: String, defaultValue: String = "") : String = preferences(context)
        .getString(key, defaultValue)