package bloder.com.twitter.search_user

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bloder.com.domain.models.sentiment.SENTIMENT
import bloder.com.twitter.R


class SentimentDialog @SuppressLint("ValidFragment")
constructor(private val backGroundColor: Int, private val emoji: Int, private val description: String) : DialogFragment() {

    companion object {
        fun get(sentiment: SENTIMENT) : SentimentDialog = when(sentiment) {
            SENTIMENT.HAPPY -> SentimentDialog(Color.parseColor("#FFC107"), R.drawable.happy_emoji, "This is a happy tweet!")
            SENTIMENT.NEUTRAL -> SentimentDialog(Color.parseColor("#9E9E9E"), R.drawable.neutral_emoji, "This is a neutral tweet")
            SENTIMENT.SAD -> SentimentDialog(Color.parseColor("#1976D2"), R.drawable.sad_emoji, "This is a sad tweet.")
        }
    }

    constructor() : this(0, 0, "")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = activity?.layoutInflater?.inflate(R.layout.sentiment_dialog, LinearLayout(activity), false)
        dialogView?.findViewById<View>(R.id.close)?.setOnClickListener { dismiss() }
        dialogView?.setBackgroundColor(backGroundColor)
        dialogView?.findViewById<ImageView>(R.id.sentiment_emoji)?.let { it shows emoji }
        dialogView?.findViewById<TextView>(R.id.sentiment_description)?.let { it shows description }
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)
        return dialog
    }
}