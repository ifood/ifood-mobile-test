package br.com.tweetanalyzer.util

import android.util.Base64

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class Util {
    companion object {
        fun getBase64(s: String): String = Base64.encodeToString(s.toByteArray(charset("UTF-8")), Base64.NO_WRAP)
    }
}