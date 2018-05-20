package br.com.tweetanalyzer.twitterApi

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
class TwitterConstants {
    companion object {
        private const val CONSUMER_KEY = "zdQVsYmWytX9TvwzREFZRkQ5T"
        private const val CONSUMER_SECRET = "cecjFZbBvgqA8al55kENP9DmMO6BGjr0uL9A3KZjF4rrNMI8AE"

        const val TOKEN_TWITTER = "$CONSUMER_KEY:$CONSUMER_SECRET"

        const val SEARCH_URL_TWITTER = "https://api.twitter.com"

        const val GET_AUTH_TWIITER = "/oauth2/token"
        const val SEARCH_USER_INFO = "/1.1/users/show.json"
        const val SEARCH_USER_TIMELINE = "/1.1/statuses/user_timeline.json"
    }
}