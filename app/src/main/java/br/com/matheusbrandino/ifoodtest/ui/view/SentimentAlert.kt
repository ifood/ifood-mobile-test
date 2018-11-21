package br.com.matheusbrandino.ifoodtest.ui.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import br.com.matheusbrandino.ifoodtest.R
import br.com.matheusbrandino.ifoodtest.model.Sentiments
import kotlinx.android.synthetic.main.alert_sentiment.view.*

class SentimentAlert(val score: Double) {

    fun show(context: Context, listener: () -> Unit) {

        val sentiment = Sentiments.getInstance(score)

        val view = createCustomView(context, sentiment)

        AlertDialog.Builder(context)
            .setView(view)
            .setOnDismissListener { listener() }
            .show()

    }

    private fun createCustomView(
        context: Context,
        sentiment: Sentiments
    ): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.alert_sentiment, null)

        view.analyzeParent.setBackgroundColor(Color.parseColor(sentiment.color))
        view.analyzeText.text = sentiment.emoji
        return view
    }
}