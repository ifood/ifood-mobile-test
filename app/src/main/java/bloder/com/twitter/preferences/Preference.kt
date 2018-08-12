package bloder.com.twitter.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_REF = "twitter_pref"

private fun preference(context: Context) : SharedPreferences = context.getSharedPreferences(PREFERENCE_REF, Context.MODE_PRIVATE)

fun save(context: Context, key: String, value: String) = preference(context)
        .edit()
        .putString(key, value)
        .apply()

fun fetch(context: Context, key: String) : String = preference(context)
        .getString(key, "")