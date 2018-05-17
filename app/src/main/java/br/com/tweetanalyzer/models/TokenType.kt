package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
data class TokenType(@SerializedName("token_type") val tokenType: String,
                     @SerializedName("access_token")val token: String) {

    fun isValid() : Boolean = !token.isEmpty() && !tokenType.isEmpty()
}