package testCommon.utils

data class JsonMockFile(val path: String, val fileName: String)

object JsonFile {

    object Google {
        private const val PATH = "google/"

        val ANALYZE_SENTIMENT_HAPPY = JsonMockFile(PATH, "analyze-sentiment-happy.json")
        val ANALYZE_SENTIMENT_NEUTRAL = JsonMockFile(PATH, "analyze-sentiment-neutral.json")
        val ANALYZE_SENTIMENT_SAD = JsonMockFile(PATH, "analyze-sentiment-sad.json")
        val ANALYZE_SENTIMENT_BROKEN = JsonMockFile(PATH, "analyze-sentiment-broken.json")
    }

    object Twitter {
        private const val PATH = "twitter/"

        val SEARCH_USER = JsonMockFile(PATH, "search-user.json")

        val USER_TIMELINE = JsonMockFile(PATH, "user-timeline.json")
    }
}
