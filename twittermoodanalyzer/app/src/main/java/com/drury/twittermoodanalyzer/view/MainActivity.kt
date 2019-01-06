package com.drury.twittermoodanalyzer.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.drury.twittermoodanalyzer.BaseApp
import com.drury.twittermoodanalyzer.R
import com.drury.twittermoodanalyzer.controller.TweetController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tweetController = TweetController(applicationContext as BaseApp)

        tweetController.getTweetsByUsername("carlos_daniel")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    success -> Log.d("Sucesso!", success.toString())
                }, {
                    fail -> Log.d("FAILLLLL!", fail.toString())
                })

    }
}
