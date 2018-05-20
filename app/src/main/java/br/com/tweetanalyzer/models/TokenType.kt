package br.com.tweetanalyzer.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
data class TokenType(@Expose @SerializedName("token_type") val tokenType: String,
                     @Expose @SerializedName("access_token") val token: String)