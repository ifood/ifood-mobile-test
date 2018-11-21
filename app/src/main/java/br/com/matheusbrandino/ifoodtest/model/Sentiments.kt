package br.com.matheusbrandino.ifoodtest.model

enum class Sentiments(val emoji: String, val color: String) {

    HAPPY("\uD83D\uDE03", "#FFEF00"),
    NEUTRAL("\uD83D\uDE10", "#AFAFAF"),
    SAD("\uD83D\uDE14", "#4999E9");

    companion object {

        fun getInstance(score: Double) = when (score) {
            in -1.0..-0.25 -> SAD
            in 0.25..1.0 -> HAPPY
            else -> NEUTRAL

        }
    }

}