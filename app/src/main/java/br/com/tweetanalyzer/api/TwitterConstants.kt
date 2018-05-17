package br.com.tweetanalyzer.api

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class TwitterConstants {
    companion object {
        const val CONSUMER_KEY = "zdQVsYmWytX9TvwzREFZRkQ5T"
        const val CONSUMER_SECRET = "cecjFZbBvgqA8al55kENP9DmMO6BGjr0uL9A3KZjF4rrNMI8AE"

        const val SEARCH_URL_TWITTER = "https://api.twitter.com"
        const val TOKEN_TWITTER = "$CONSUMER_KEY:$CONSUMER_SECRET"
        const val SEARCH_CODE_TWITTER = "/1.1/search/tweets.json"
        const val SEARCH_USER_TIMELINE = "/1.1/statuses/user_timeline.json"
    }
}