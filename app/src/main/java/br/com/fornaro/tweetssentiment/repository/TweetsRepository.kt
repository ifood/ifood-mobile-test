package br.com.fornaro.tweetssentiment.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.fornaro.tweetssentiment.model.Tweet

class TweetsRepository {

    fun getTweets(username: String): LiveData<List<Tweet>>? {
        val data = MutableLiveData<List<Tweet>>()

        val list = mutableListOf<Tweet>()
        list.add(Tweet("This is a sad tweet"))
        list.add(Tweet("This is a newtral tweet"))
        list.add(Tweet("This is a happy tweet"))
        data.value = list

        return data
    }
}