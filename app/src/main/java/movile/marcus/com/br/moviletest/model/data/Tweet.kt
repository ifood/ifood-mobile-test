package movile.marcus.com.br.moviletest.model.data

import com.squareup.moshi.Json

enum class SentimentalEnum {
    HAPPY, SAD, NEUTRAL
}

data class TweetData(
    @field:Json(name = "created_at") val createdAt: String?,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "text") val text: String?,
    @field:Json(name = "user") val user: UserResult?
)

class TweetAnalyzer(private val score: Double) {
    fun getSentimental(): SentimentalEnum {
        return when (score) {
            in (-1.0)..(-0.05) -> SentimentalEnum.SAD
            in (0.05)..(1.0) -> SentimentalEnum.HAPPY
            else -> SentimentalEnum.NEUTRAL
        }
    }
}