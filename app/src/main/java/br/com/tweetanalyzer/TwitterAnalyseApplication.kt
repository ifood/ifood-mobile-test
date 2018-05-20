package br.com.tweetanalyzer

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig

/**
 * Created by gabrielsamorim
 * on 20/05/18.
 */
class TwitterAnalyseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        EmojiCompat.init(BundledEmojiCompatConfig(this))
    }
}