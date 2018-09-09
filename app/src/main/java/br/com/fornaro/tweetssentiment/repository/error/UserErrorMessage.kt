package br.com.fornaro.tweetssentiment.repository.error

import android.content.Context
import br.com.fornaro.tweetssentiment.R

object UserErrorMessage {

    @JvmStatic
    fun getUserErrorMessage(context: Context, code: Int): String {
        return when (code) {
            404 -> context.getString(R.string.user_not_found)
            else -> context.getString(R.string.general_error)
        }
    }
}