package com.test.ifood.twitterhumour.tweetlist.humour.viewmodel

import android.app.Application
import android.databinding.Bindable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.test.ifood.twitterhumour.BR
import com.test.ifood.twitterhumour.R
import com.test.ifood.twitterhumour.base.BaseView
import com.test.ifood.twitterhumour.base.BaseViewModel
import kotlin.properties.Delegates

class HumourViewModel(application: Application): BaseViewModel<BaseView?>(application) {

    @get:Bindable
    var sentiment by Delegates.observable(" ") { _, _, _ ->
        registry.notifyChange(this, BR.sentiment)
    }


    val bgColor: Int
        @Bindable("sentiment")
        get() {
            if(sentiment.equals("positive")) {
                return ContextCompat.getColor(getApplication(), R.color.green)
            } else if (sentiment.equals("negative")) {
                return ContextCompat.getColor(getApplication(), R.color.blue)
            }

            return ContextCompat.getColor(getApplication(), R.color.gray)
        }

    val emoji: Drawable?
        @Bindable("sentiment")
        get() {

            if(sentiment == "positive") {
                return ContextCompat.getDrawable(getApplication(), R.drawable.ic_sentiment_satisfied_white_24dp)
            } else if (sentiment == "negative") {
                return ContextCompat.getDrawable(getApplication(), R.drawable.ic_sentiment_dissatisfied_white_24dp)
            }

            return ContextCompat.getDrawable(getApplication(), R.drawable.ic_sentiment_neutral_black_24dp)
        }

}