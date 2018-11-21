package br.com.matheusbrandino.ifoodtest.app

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import br.com.matheusbrandino.ifoodtest.di.modules
import org.koin.android.ext.android.startKoin


class IFoodTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)

        enableEmoji()
    }

    private fun enableEmoji() {
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
    }
}