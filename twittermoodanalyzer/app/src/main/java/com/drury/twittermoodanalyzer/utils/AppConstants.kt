package com.drury.twittermoodanalyzer.utils

import com.drury.twittermoodanalyzer.R

object AppConstants {

    const val REQUEST_TIMEOUT: Long = 30
    const val MEDIA_TYPE_JSON = "application/json"
    const val BASE_URL_TWITTER_API = "https://api.twitter.com/"
    const val BASE_URL_GCP_CLOUD_LANGUAGE_NATURAL = "https://language.googleapis.com/"

    // Check the emoji unicode list here:
    // https://apps.timwhitlock.info/emoji/tables/unicode
    const val EMOJI_SAD = 0x1F614
    const val EMOJI_HAPPY = 0x1F603
    const val EMOJI_NEUTRAL = 0x1F610

    // Colors
    const val COLOR_HAPPY = R.color.colorYellowHappy
    const val COLOR_NEUTRAL = R.color.colorGrayNeutral
    const val COLOR_SAD = R.color.colorBlueSad

    // Parcelable key
    const val TWEET = "TWEET"
    const val TWEETS = "TWEETS"

    // Animations
    const val ANIMATION_TIME = 2000L
}