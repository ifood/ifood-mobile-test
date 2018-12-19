package com.example.tweetanalyzer.model

import androidx.annotation.ColorRes
import com.example.tweetanalyzer.R

class Sentiment(
        val score: Double
){

    fun toText():String{
       return when(this.score){
           in -1.0..-0.25 -> "This is a Sad Tweet \uD83D\uDE14" //😔
           in -0.24..0.24 -> "This is a Neutral Tweet \uD83D\uDE10" //😐
           in 0.25..1.0 -> "This is a Happy Tweet \uD83D\uDE03" //😃
           else -> "This is a Neutral Tweet \uD83D\uDE10" //😐
       }
    }

    @ColorRes
    fun toColor():Int{
        return when(this.score){
            in -1.0..-0.25 -> R.color.sadBlue
            in -0.24..0.24 -> R.color.neutralGrey
            in 0.25..1.0 -> R.color.happYellow
            else -> R.color.neutralGrey
        }
    }

}