package bloder.com.twitter.preferences

import android.content.Context

private const val TWITTER_AUTH_TOKEN = "TWITTER_AUTH_TOKEN"

class AuthTokenPreferences {

    fun persistTwitterAuthToken(context: Context, token: String) = save(context, TWITTER_AUTH_TOKEN, token)
    fun getAuthToken(context: Context) : String = fetch(context, TWITTER_AUTH_TOKEN)
}