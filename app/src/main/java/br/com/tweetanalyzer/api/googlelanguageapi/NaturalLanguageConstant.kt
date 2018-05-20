package br.com.tweetanalyzer.api.googlelanguageapi

/**
 * Created by gabrielsamorim
 * on 19/05/18.
 */
class NaturalLanguageConstant {
    companion object {

        const val SEARCH_URL = "/v1/documents:analyzeSentiment"
        const val GOOGLE_NATURAL_API_URL = "https://language.googleapis.com"
        const val GOOGLE_API_KEY = "AIzaSyBrB7R3eogSkMiFzl-F89SZRfsqWfCwo7c"


        val BAD_SCORE = -1.0..-0.25
        val NORMAL_SCORE = -0.25..0.25
        val GOD_SCORE = 0.25..1.0
    }
}