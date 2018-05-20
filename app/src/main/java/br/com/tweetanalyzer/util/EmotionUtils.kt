package br.com.tweetanalyzer.util

/**
 * Created by gabrielsamorim
 * on 20/05/18.
 */
class EmotionUtils {

    companion object {
        val HAPPY_EMOTION = 0x1F601
        val NEUTRAL_EMOTION = 0x1F610
        val SAD_EMOTION = 0x1F61
    }

    fun getEmotion(unicode: Int) = String(Character.toChars(unicode))
}