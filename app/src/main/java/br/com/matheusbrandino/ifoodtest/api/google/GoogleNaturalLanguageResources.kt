package br.com.matheusbrandino.ifoodtest.api.google

import br.com.matheusbrandino.ifoodtest.model.Tweet
import com.google.gson.annotations.SerializedName

class GoogleRequest private constructor(
    val document: Document,
    val encodingType: String = "UTF8"
) {


    companion object {
        fun create(tweet: Tweet): GoogleRequest {
            return GoogleRequest(Document(tweet.text))
        }
    }
}

class Document(
    val content: String,
    val language: String = "en",
    val type: String = "PLAIN_TEXT"
)

class GoogleResponse(@SerializedName("documentSentiment") val sentiment: Sentiment)

class Sentiment(val score: Double, val magnitude: Double)