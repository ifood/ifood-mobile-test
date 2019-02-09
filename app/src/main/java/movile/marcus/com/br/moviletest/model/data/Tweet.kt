package movile.marcus.com.br.moviletest.model.data

import com.squareup.moshi.Json

enum class SentimentEnum {
    HAPPY, SAD, NEUTRAL
}

data class TweetData(
    @field:Json(name = "id") val id: Int
)

class TweetAnalyzer(private val score: Double) {
    fun getSentimental(): SentimentEnum {
        return when (score) {
            in (-1.0)..(-0.05) -> SentimentEnum.SAD
            in (0.05)..(1.0) -> SentimentEnum.HAPPY
            else -> SentimentEnum.NEUTRAL
        }
    }
}