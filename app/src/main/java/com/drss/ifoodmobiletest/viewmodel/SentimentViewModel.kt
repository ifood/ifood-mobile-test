package com.drss.ifoodmobiletest.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.drss.ifoodmobiletest.data.Sentiment
import com.drss.ifoodmobiletest.data.enums.MoodEnum
import com.drss.ifoodmobiletest.repository.GCloudSentimentApi
import com.drss.ifoodmobiletest.repository.interfaces.GCloudApiInterface
import com.twitter.sdk.android.core.models.Tweet
import kotlinx.android.synthetic.main.activity_sentiment.*
import javax.inject.Inject
import javax.inject.Singleton

class SentimentViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var gCloudSentimentApi: GCloudSentimentApi

    var text: MutableLiveData<String> = MutableLiveData()
    var date: MutableLiveData<String> = MutableLiveData()
    var sentimentResult: LiveData<Sentiment> = Transformations.switchMap(text) {
        gCloudSentimentApi.analyseSentiment(it)
    }

    var textSentimentType: LiveData<MoodEnum> = Transformations.switchMap(sentimentResult) {
        val sentimentType = MutableLiveData<MoodEnum>()
        when {
            it.score > 0.0f -> sentimentType.value = MoodEnum.HAPPY
            it.score < 0.0f -> sentimentType.value = MoodEnum.SAD
            else -> sentimentType.value = MoodEnum.NEUTRAL
        }
        sentimentType
    }

}