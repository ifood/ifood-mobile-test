package com.drss.ifoodmobiletest.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.drss.ifoodmobiletest.repository.TwitterDataSource
import com.twitter.sdk.android.core.models.Tweet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTweetsViewModel @Inject constructor(): ViewModel() {

    //Injetar
    var twitterRepository: TwitterDataSource = TwitterDataSource()
    val searchUserInput: MutableLiveData<String> = MutableLiveData()

    var searchUserResult: LiveData<List<Tweet>> = Transformations.switchMap(searchUserInput) {
        if (it.length >= 5) {
            twitterRepository.getUserTimeline(it)
        } else {
            MutableLiveData<List<Tweet>>()
        }
    }

    fun search() {
        val input = searchUserInput.value
        if (!TextUtils.isEmpty(input)) {
            searchUserResult = twitterRepository.getUserTimeline(searchUserInput.value!!)
        }
    }



}