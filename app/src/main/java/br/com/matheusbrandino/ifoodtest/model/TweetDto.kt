package br.com.matheusbrandino.ifoodtest.model

import com.google.gson.annotations.SerializedName

class TweetDto(
    @SerializedName("statuses") val tweets: List<Tweet>
)