package br.com.matheusbrandino.ifoodtest.data.repository

import android.arch.lifecycle.MutableLiveData
import br.com.matheusbrandino.ifoodtest.api.google.GoogleApi
import br.com.matheusbrandino.ifoodtest.api.google.GoogleRequest
import br.com.matheusbrandino.ifoodtest.api.google.Sentiment
import br.com.matheusbrandino.ifoodtest.model.Tweet

class GoogleRepository(private val googleApi: GoogleApi) {


    val sentiment: MutableLiveData<Sentiment> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()

    fun analyse(tweet: Tweet) {

        val request = GoogleRequest.create(tweet)

        googleApi.analyseTweet(request, sucess, fallback)
    }

    fun clean() = sentiment.postValue(null)


    private val sucess: (Sentiment) -> Unit = { sentiment.postValue(it) }

    private val fallback: (Throwable) -> Unit = { error.postValue(it) }

}