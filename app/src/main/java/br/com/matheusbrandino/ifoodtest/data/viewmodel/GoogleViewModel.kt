package br.com.matheusbrandino.ifoodtest.data.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.matheusbrandino.ifoodtest.api.google.Sentiment
import br.com.matheusbrandino.ifoodtest.data.repository.GoogleRepository
import br.com.matheusbrandino.ifoodtest.model.Tweet

class GoogleViewModel(private val googleRepository: GoogleRepository) : ViewModel() {

    val sentiment: MutableLiveData<Sentiment> = googleRepository.sentiment
    fun analyse(tweet: Tweet) = googleRepository.analyse(tweet)
    fun clean() = googleRepository.clean()
}
